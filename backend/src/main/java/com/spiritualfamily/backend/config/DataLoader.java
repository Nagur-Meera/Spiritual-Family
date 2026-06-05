package com.spiritualfamily.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spiritualfamily.backend.entity.enums.RoleType;
import com.spiritualfamily.backend.entity.user.Role;
import com.spiritualfamily.backend.repository.user.RoleRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader
        implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args)
            throws Exception {

        if (roleRepository.count() == 0) {

            roleRepository.save(
                    Role.builder()
                            .name(RoleType.ADMIN)
                            .build()
            );

            roleRepository.save(
                    Role.builder()
                            .name(RoleType.MEMBER)
                            .build()
            );
        }
    }
}