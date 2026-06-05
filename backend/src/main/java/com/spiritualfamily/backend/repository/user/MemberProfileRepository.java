// ======================================================
// repository/user/MemberProfileRepository.java
// ======================================================

package com.spiritualfamily.backend.repository.user;

import com.spiritualfamily.backend.entity.user.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProfileRepository
        extends JpaRepository<MemberProfile, Long> {
}