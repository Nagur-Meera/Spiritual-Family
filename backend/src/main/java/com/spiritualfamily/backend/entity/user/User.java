// COMPLETE FIX VERSION

package com.spiritualfamily.backend.entity.user;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String phone;

    @Builder.Default
    @Column(nullable = false)
    private Boolean emailVerified = false;

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

    private String profilePhotoUrl;

    @Builder.Default
    private LocalDateTime createdAt =
            LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updatedAt =
            LocalDateTime.now();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",

            joinColumns = @JoinColumn(name = "user_id"),

            inverseJoinColumns =
            @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}