package com.spiritualfamily.backend.repository.bible;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.bible.Devotion;

public interface DevotionRepository
        extends JpaRepository<Devotion, Long> {
}