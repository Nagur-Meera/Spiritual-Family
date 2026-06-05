package com.spiritualfamily.backend.repository.bible;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.bible.BibleVerse;

public interface BibleVerseRepository
        extends JpaRepository<BibleVerse, Long> {
}