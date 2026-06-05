package com.spiritualfamily.backend.service.auth;

import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spiritualfamily.backend.dto.auth.AuthResponse;
import com.spiritualfamily.backend.dto.auth.LoginRequest;
import com.spiritualfamily.backend.dto.auth.RegisterRequest;
import com.spiritualfamily.backend.entity.enums.RoleType;
import com.spiritualfamily.backend.entity.user.Role;
import com.spiritualfamily.backend.entity.user.User;
import com.spiritualfamily.backend.repository.user.RoleRepository;
import com.spiritualfamily.backend.repository.user.UserRepository;
import com.spiritualfamily.backend.security.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final EmailVerificationService emailVerificationService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role memberRole = roleRepository.findByName(RoleType.MEMBER).orElseThrow();

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .emailVerified(false)
                .roles(Set.of(memberRole))
                .build();

        userRepository.save(user);

        String verificationToken = emailVerificationService.createVerificationToken(user);
        emailVerificationService.sendVerificationEmail(user, verificationToken);

        return AuthResponse.builder()
                .message("Registration successful. Check your email for verification link.")
                .build();
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        if (user.getActive() == null || !user.getActive()) {
            throw new RuntimeException("Account is blocked");
        }

        if (user.getEmailVerified() == null || !user.getEmailVerified()) {
            throw new RuntimeException("Please verify your email before logging in");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String refreshToken = refreshTokenService.createRefreshToken(user).getToken();

        String token = jwtService.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        java.util.List.of()
                )
        );

        return AuthResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .message("Login successful")
                .build();
    }
}