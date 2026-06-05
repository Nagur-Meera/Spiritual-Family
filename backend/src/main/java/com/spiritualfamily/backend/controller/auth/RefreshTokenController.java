package com.spiritualfamily.backend.controller.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spiritualfamily.backend.dto.auth.RefreshTokenRequest;
import com.spiritualfamily.backend.dto.auth.RefreshTokenResponse;
import com.spiritualfamily.backend.service.auth.RefreshTokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final RefreshTokenService
            refreshTokenService;

    @PostMapping("/refresh")
    public RefreshTokenResponse refresh(
            @RequestBody
            RefreshTokenRequest request
    ) {

        return refreshTokenService.refresh(
                request.getRefreshToken()
        );
    }
}