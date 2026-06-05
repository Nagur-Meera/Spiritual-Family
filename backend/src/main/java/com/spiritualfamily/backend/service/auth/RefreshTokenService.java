package com.spiritualfamily.backend.service.auth;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.spiritualfamily.backend.dto.auth.RefreshTokenResponse;
import com.spiritualfamily.backend.entity.auth.RefreshToken;
import com.spiritualfamily.backend.entity.user.User;
import com.spiritualfamily.backend.repository.token.RefreshTokenRepository;
import com.spiritualfamily.backend.security.jwt.CustomUserDetailsService;
import com.spiritualfamily.backend.security.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository
            refreshTokenRepository;

    private final JwtService jwtService;

    private final CustomUserDetailsService userDetailsService;

    public RefreshToken createRefreshToken(
            User user
    ) {

        RefreshToken refreshToken =
                RefreshToken.builder()
                        .token(
                                UUID.randomUUID()
                                        .toString()
                        )
                        .user(user)
                        .expiryDate(
                                LocalDateTime.now()
                                        .plusDays(7)
                        )
                        .build();

        return refreshTokenRepository.save(
                refreshToken
        );
    }

    public RefreshTokenResponse refresh(
            String token
    ) {

        RefreshToken refreshToken =
                refreshTokenRepository
                        .findByToken(token)
                        .orElseThrow();

        if(refreshToken.getExpiryDate()
                .isBefore(LocalDateTime.now())) {

            throw new RuntimeException(
                    "Refresh token expired"
            );
        }

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(
                        refreshToken.getUser().getEmail()
                );

        refreshTokenRepository.delete(refreshToken);

        RefreshToken newRefreshToken =
                createRefreshToken(refreshToken.getUser());

        String accessToken =
                jwtService.generateToken(
                        userDetails
                );

        return RefreshTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken.getToken())
                .build();
    }

    public void logout(
            String token
    ) {

        refreshTokenRepository
                .deleteByToken(token);
    }
}