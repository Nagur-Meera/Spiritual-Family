package com.spiritualfamily.backend.controller.prayer;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spiritualfamily.backend.dto.prayer.PrayerNoteDto;
import com.spiritualfamily.backend.dto.prayer.PrayerRequestDto;
import com.spiritualfamily.backend.entity.enums.PrayerStatus;
import com.spiritualfamily.backend.entity.prayer.PrayerNote;
import com.spiritualfamily.backend.entity.prayer.PrayerRequest;
import com.spiritualfamily.backend.service.prayer.PrayerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/prayers")
@RequiredArgsConstructor
public class PrayerController {

    private final PrayerService service;

    @PostMapping
    public PrayerRequest createRequest(
            @RequestBody PrayerRequestDto dto
    ) {

        return service.createRequest(dto);
    }

    @PostMapping("/note")
    public PrayerNote addNote(
            @RequestBody PrayerNoteDto dto
    ) {

        return service.addNote(dto);
    }

    @GetMapping
    public List<PrayerRequest> getAllRequests() {

        return service.getAllRequests();
    }

    @PutMapping("/{id}/status")
    public PrayerRequest updateStatus(
            @PathVariable Long id,
            @RequestParam PrayerStatus status
    ) {

        return service.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public String deleteRequest(
            @PathVariable Long id
    ) {

        service.deleteRequest(id);

        return "Prayer Request Deleted";
    }
}