package com.spiritualfamily.backend.logging;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RequestLoggingFilter
        implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    )
            throws IOException, ServletException {

        HttpServletRequest req =
                (HttpServletRequest) request;

        log.info(
                "Request => {} {}",
                req.getMethod(),
                req.getRequestURI()
        );

        chain.doFilter(request, response);
    }
}