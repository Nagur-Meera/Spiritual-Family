package com.spiritualfamily.backend.controller.bible;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spiritualfamily.backend.dto.bible.BibleVerseRequest;
import com.spiritualfamily.backend.dto.bible.DevotionRequest;
import com.spiritualfamily.backend.entity.bible.BibleVerse;
import com.spiritualfamily.backend.entity.bible.Devotion;
import com.spiritualfamily.backend.service.bible.BibleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bible")
@RequiredArgsConstructor
public class BibleController {

    private final BibleService service;

    @PostMapping("/verse")
    public BibleVerse createVerse(
            @RequestBody BibleVerseRequest request
    ) {

        return service.createVerse(request);
    }

    @PostMapping("/devotion")
    public Devotion createDevotion(
            @RequestBody DevotionRequest request
    ) {

        return service.createDevotion(request);
    }

    @GetMapping("/verses")
    public List<BibleVerse> getAllVerses() {

        return service.getAllVerses();
    }

    @GetMapping("/devotions")
    public List<Devotion> getAllDevotions() {

        return service.getAllDevotions();
    }

    @DeleteMapping("/verse/{id}")
    public String deleteVerse(
            @PathVariable Long id
    ) {

        service.deleteVerse(id);

        return "Verse Deleted";
    }

    @DeleteMapping("/devotion/{id}")
    public String deleteDevotion(
            @PathVariable Long id
    ) {

        service.deleteDevotion(id);

        return "Devotion Deleted";
    }
}