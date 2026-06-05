package com.spiritualfamily.backend.repository.prayer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.prayer.PrayerNote;

public interface PrayerNoteRepository
        extends JpaRepository<PrayerNote, Long> {
}