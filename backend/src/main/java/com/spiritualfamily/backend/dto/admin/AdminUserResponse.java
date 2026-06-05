package com.spiritualfamily.backend.dto.admin;

import java.util.Set;

import com.spiritualfamily.backend.entity.enums.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUserResponse {

    private Long id;

    private String fullName;

    private String email;

    private String phone;

    private boolean emailVerified;

    private boolean active;

    private Set<RoleType> roles;
}
