package com.spiritualfamily.backend.initializer;

import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.spiritualfamily.backend.entity.enums.RoleType;
import com.spiritualfamily.backend.entity.user.Role;
import com.spiritualfamily.backend.entity.user.User;
import com.spiritualfamily.backend.repository.user.RoleRepository;
import com.spiritualfamily.backend.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements ApplicationRunner {

	private final RoleRepository roleRepository;

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	@Value("${app.admin.email:}")
	private String adminEmail;

	@Value("${app.admin.password:}")
	private String adminPassword;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		// Ensure roles exist
		for (RoleType roleType : RoleType.values()) {
			roleRepository.findByName(roleType)
					.orElseGet(() -> roleRepository.save(
							Role.builder().name(roleType).build()
					));
		}

		// Optionally seed admin user if credentials provided and user doesn't exist
		if (adminEmail != null && !adminEmail.isBlank()
				&& adminPassword != null && !adminPassword.isBlank()) {

			if (!userRepository.existsByEmail(adminEmail)) {

				Role adminRole = roleRepository.findByName(RoleType.ADMIN).orElseThrow();

				User admin = User.builder()
						.fullName("Administrator")
						.email(adminEmail)
						.password(passwordEncoder.encode(adminPassword))
						.emailVerified(true)
						.roles(Set.of(adminRole))
						.build();

				userRepository.save(admin);
			}
		}
	}
}