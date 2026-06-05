package com.spiritualfamily.backend.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {

    @NotBlank(message = "Current password required")
    private String currentPassword;

    @NotBlank(message = "New password required")
    @Size(min = 6, message = "Password minimum 6 chars")
    private String newPassword;
}