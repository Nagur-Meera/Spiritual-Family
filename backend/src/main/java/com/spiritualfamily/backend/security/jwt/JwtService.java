package com.spiritualfamily.backend.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final Key key =
            Keys.hmacShaKeyFor(
                    JwtConstants.SECRET.getBytes()
            );

    public String generateToken(
            UserDetails userDetails
    ) {

        Date now = new Date();

        Date expiry =
                new Date(
                        now.getTime()
                                + JwtConstants.JWT_EXPIRATION
                );

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key)
                .compact();
    }

    public String extractUsername(
            String token
    ) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(
            String token,
            UserDetails userDetails
    ) {

        String username =
                extractUsername(token);

        return username.equals(
                userDetails.getUsername()
        );
    }
}