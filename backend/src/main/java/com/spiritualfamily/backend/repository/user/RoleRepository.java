// ======================================================
// repository/user/RoleRepository.java
// ======================================================

package com.spiritualfamily.backend.repository.user;

import com.spiritualfamily.backend.entity.enums.RoleType;
import com.spiritualfamily.backend.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleType name);
}