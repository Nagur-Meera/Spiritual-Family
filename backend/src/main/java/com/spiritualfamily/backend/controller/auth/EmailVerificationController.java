package com.spiritualfamily.backend.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spiritualfamily.backend.service.auth.EmailVerificationService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication APIs")
public class EmailVerificationController {

    private final EmailVerificationService verificationService;

    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam("token") String token) {

        boolean ok = verificationService.verifyToken(token);

        if (ok) {
            return ResponseEntity.ok("Email verified successfully");
        }

        return ResponseEntity.badRequest().body("Invalid or expired token");
    }
}
