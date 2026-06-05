// ======================================================
// repository/user/PrayerInterestRepository.java
// ======================================================

package com.spiritualfamily.backend.repository.user;

import com.spiritualfamily.backend.entity.user.PrayerInterest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrayerInterestRepository
        extends JpaRepository<PrayerInterest, Long> {
}