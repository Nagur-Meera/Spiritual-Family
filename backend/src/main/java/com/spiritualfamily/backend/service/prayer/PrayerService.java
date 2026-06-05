package com.spiritualfamily.backend.service.prayer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spiritualfamily.backend.dto.prayer.PrayerNoteDto;
import com.spiritualfamily.backend.dto.prayer.PrayerRequestDto;
import com.spiritualfamily.backend.entity.enums.PrayerStatus;
import com.spiritualfamily.backend.entity.prayer.PrayerNote;
import com.spiritualfamily.backend.entity.prayer.PrayerRequest;
import com.spiritualfamily.backend.entity.user.User;
import com.spiritualfamily.backend.repository.prayer.PrayerNoteRepository;
import com.spiritualfamily.backend.repository.prayer.PrayerRequestRepository;
import com.spiritualfamily.backend.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrayerService {

    private final PrayerRequestRepository prayerRequestRepository;

    private final PrayerNoteRepository prayerNoteRepository;

    private final UserRepository userRepository;

    public PrayerRequest createRequest(
            PrayerRequestDto dto
    ) {

        User user =
                userRepository.findById(
                        dto.getUserId()
                ).orElseThrow();

        PrayerRequest request =
                PrayerRequest.builder()
                        .title(dto.getTitle())
                        .requestText(dto.getRequestText())
                        .status(PrayerStatus.PENDING)
                        .user(user)
                        .build();

        return prayerRequestRepository.save(request);
    }

    public PrayerNote addNote(
            PrayerNoteDto dto
    ) {

        PrayerRequest request =
                prayerRequestRepository.findById(
                        dto.getPrayerRequestId()
                ).orElseThrow();

        PrayerNote note =
                PrayerNote.builder()
                        .note(dto.getNote())
                        .prayerRequest(request)
                        .build();

        return prayerNoteRepository.save(note);
    }

    public List<PrayerRequest> getAllRequests() {

        return prayerRequestRepository.findAll();
    }

    public PrayerRequest updateStatus(
            Long id,
            PrayerStatus status
    ) {

        PrayerRequest request =
                prayerRequestRepository.findById(id)
                        .orElseThrow();

        request.setStatus(status);

        return prayerRequestRepository.save(request);
    }

    public void deleteRequest(Long id) {

        prayerRequestRepository.deleteById(id);
    }
}