package com.spiritualfamily.backend.service.bible;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spiritualfamily.backend.dto.bible.BibleVerseRequest;
import com.spiritualfamily.backend.dto.bible.DevotionRequest;
import com.spiritualfamily.backend.entity.bible.BibleVerse;
import com.spiritualfamily.backend.entity.bible.Devotion;
import com.spiritualfamily.backend.repository.bible.BibleVerseRepository;
import com.spiritualfamily.backend.repository.bible.DevotionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BibleService {

    private final BibleVerseRepository bibleVerseRepository;

    private final DevotionRepository devotionRepository;

    public BibleVerse createVerse(
            BibleVerseRequest request
    ) {

        BibleVerse verse =
                BibleVerse.builder()
                        .verseReference(request.getVerseReference())
                        .verseText(request.getVerseText())
                        .verseDate(request.getVerseDate())
                        .build();

        return bibleVerseRepository.save(verse);
    }

    public Devotion createDevotion(
            DevotionRequest request
    ) {

        Devotion devotion =
                Devotion.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .build();

        return devotionRepository.save(devotion);
    }

    public List<BibleVerse> getAllVerses() {

        return bibleVerseRepository.findAll();
    }

    public List<Devotion> getAllDevotions() {

        return devotionRepository.findAll();
    }

    public void deleteVerse(Long id) {

        bibleVerseRepository.deleteById(id);
    }

    public void deleteDevotion(Long id) {

        devotionRepository.deleteById(id);
    }
}