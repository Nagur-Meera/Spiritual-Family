package com.spiritualfamily.backend.controller.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spiritualfamily.backend.service.auth.RefreshTokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LogoutController {

    private final RefreshTokenService
            refreshTokenService;

    @PostMapping("/logout")
    public String logout(
            @RequestParam String refreshToken
    ) {

        refreshTokenService.logout(
                refreshToken
        );

        return "Logged out successfully";
    }
}