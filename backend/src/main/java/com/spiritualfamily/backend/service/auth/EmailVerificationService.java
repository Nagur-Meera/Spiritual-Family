package com.spiritualfamily.backend.service.auth;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spiritualfamily.backend.entity.auth.EmailVerificationToken;
import com.spiritualfamily.backend.entity.user.User;
import com.spiritualfamily.backend.repository.auth.EmailVerificationTokenRepository;
import com.spiritualfamily.backend.repository.user.UserRepository;
import com.spiritualfamily.backend.service.email.EmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final EmailVerificationTokenRepository tokenRepository;

    private final UserRepository userRepository;

    private final EmailService emailService;

    public String createVerificationToken(User user) {

        String token = UUID.randomUUID().toString();

        EmailVerificationToken verificationToken = EmailVerificationToken.builder()
                .token(token)
                .user(user)
                .expiryDate(LocalDateTime.now().plusDays(1))
                .build();

        tokenRepository.save(verificationToken);

        return token;
    }

    public void sendVerificationEmail(User user, String token) {

        String link = String.format("%s/api/auth/verify?token=%s", getAppUrl(), token);

        String body = "<p>Hello " + user.getFullName() + ",</p>"
                + "<p>Please verify your email by clicking the link below:</p>"
                + "<p><a href=\"" + link + "\">Verify Email</a></p>"
                + "<p>If you didn't register, ignore this email.</p>";

        emailService.sendHtmlEmail(user.getEmail(), "Verify your email", body);
    }

    @Transactional
    public boolean verifyToken(String token) {

        EmailVerificationToken verificationToken = tokenRepository
                .findByToken(token)
                .orElseThrow();

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            tokenRepository.delete(verificationToken);
            return false;
        }

        User user = verificationToken.getUser();
        user.setEmailVerified(true);
        userRepository.save(user);

        tokenRepository.delete(verificationToken);

        return true;
    }

    private String getAppUrl() {
        // Default to localhost; frontend may be hosted elsewhere. Adjust in production.
        return "http://localhost:8080";
    }
}
