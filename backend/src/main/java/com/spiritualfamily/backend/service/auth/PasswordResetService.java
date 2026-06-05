package com.spiritualfamily.backend.service.auth;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spiritualfamily.backend.dto.auth.ChangePasswordRequest;
import com.spiritualfamily.backend.dto.auth.ForgotPasswordRequest;
import com.spiritualfamily.backend.dto.auth.ResetPasswordRequest;
import com.spiritualfamily.backend.entity.auth.PasswordResetToken;
import com.spiritualfamily.backend.entity.user.User;
import com.spiritualfamily.backend.exception.ResourceNotFoundException;
import com.spiritualfamily.backend.repository.token.PasswordResetTokenRepository;
import com.spiritualfamily.backend.repository.user.UserRepository;
import com.spiritualfamily.backend.service.email.EmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Transactional
    public String forgotPassword(ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        PasswordResetToken token = PasswordResetToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiryDate(LocalDateTime.now().plusHours(1))
                .build();
        tokenRepository.save(token);

        String resetLink = "http://localhost:8080/swagger-ui.html#/Authentication%20APIs";
        String body = "<p>Hello " + user.getFullName() + ",</p>"
                + "<p>Use this reset token to change your password:</p>"
                + "<p><strong>" + token.getToken() + "</strong></p>"
                + "<p>Token expires in 1 hour.</p>"
                + "<p>Open Swagger to call the reset endpoint: " + resetLink + "</p>";

        emailService.sendHtmlEmail(user.getEmail(), "Password reset token", body);
        return "Password reset token sent to email";
    }

    @Transactional
    public String resetPassword(ResetPasswordRequest request) {
        PasswordResetToken token = tokenRepository.findByToken(request.getToken())
                .orElseThrow(() -> new ResourceNotFoundException("Reset token not found"));

        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            tokenRepository.delete(token);
            throw new RuntimeException("Reset token expired");
        }

        User user = token.getUser();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        tokenRepository.delete(token);
        return "Password reset successfully";
    }

    @Transactional
    public String changePassword(String email, ChangePasswordRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return "Password changed successfully";
    }
}