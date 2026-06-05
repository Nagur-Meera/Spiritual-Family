package com.spiritualfamily.backend.repository.prayer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.prayer.PrayerRequest;

public interface PrayerRequestRepository
        extends JpaRepository<PrayerRequest, Long> {
}