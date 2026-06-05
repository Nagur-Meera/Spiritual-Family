// config/DataInitializer.java

package com.spiritualfamily.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.spiritualfamily.backend.entity.enums.RoleType;
import com.spiritualfamily.backend.entity.user.Role;
import com.spiritualfamily.backend.repository.user.RoleRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        createRole(RoleType.ADMIN);
        createRole(RoleType.MODERATOR);
        createRole(RoleType.MEMBER);
    }

    private void createRole(RoleType roleType) {

        roleRepository.findByName(roleType)
                .orElseGet(() ->
                        roleRepository.save(
                                Role.builder()
                                        .name(roleType)
                                        .build()
                        )
                );
    }
}