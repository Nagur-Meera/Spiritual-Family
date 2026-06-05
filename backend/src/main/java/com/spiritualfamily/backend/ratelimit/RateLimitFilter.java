package com.spiritualfamily.backend.ratelimit;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitFilter implements Filter {

    private final StringRedisTemplate redisTemplate;

    @Value("${rate-limit.enabled:true}")
    private boolean enabled;

    @Value("${rate-limit.max-requests:1000}")
    private long maxRequests;

    @Value("${rate-limit.window-seconds:60}")
    private long windowSeconds;

    public RateLimitFilter(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        if (!enabled || !(request instanceof HttpServletRequest req)) {
            chain.doFilter(request, response);
            return;
        }

        String path = req.getRequestURI();
        if (path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/api/auth")) {
            chain.doFilter(request, response);
            return;
        }

        String clientIp = getClientIp(req);
        long windowStart = Instant.now().getEpochSecond() / windowSeconds;
        String key = "rate-limit:" + clientIp + ":" + windowStart;

        Long count = redisTemplate.opsForValue().increment(key);
        if (count != null && count == 1L) {
            redisTemplate.expire(key, Duration.ofSeconds(windowSeconds));
        }

        if (count != null && count > maxRequests) {
            HttpServletResponse res = (HttpServletResponse) response;
            res.setStatus(429);
            res.setContentType("application/json");
            res.getWriter().write("{\"message\":\"Too many requests\"}");
            return;
        }

        chain.doFilter(request, response);
    }

    private String getClientIp(HttpServletRequest request) {

        String forwardedFor = request.getHeader("X-Forwarded-For");
        if (forwardedFor != null && !forwardedFor.isBlank()) {
            return forwardedFor.split(",")[0].trim();
        }

        String realIp = request.getHeader("X-Real-IP");
        if (realIp != null && !realIp.isBlank()) {
            return realIp.trim();
        }

        return request.getRemoteAddr();
    }
}
