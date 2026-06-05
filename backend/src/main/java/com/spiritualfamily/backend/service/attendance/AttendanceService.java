package com.spiritualfamily.backend.service.attendance;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.spiritualfamily.backend.dto.attendance.AttendanceRecordRequest;
import com.spiritualfamily.backend.dto.attendance.AttendanceSessionRequest;
import com.spiritualfamily.backend.entity.attendance.AttendanceRecord;
import com.spiritualfamily.backend.entity.attendance.AttendanceSession;
import com.spiritualfamily.backend.entity.user.User;
import com.spiritualfamily.backend.exception.BadRequestException;
import com.spiritualfamily.backend.repository.attendance.AttendanceRecordRepository;
import com.spiritualfamily.backend.repository.attendance.AttendanceSessionRepository;
import com.spiritualfamily.backend.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceSessionRepository sessionRepository;

    private final AttendanceRecordRepository recordRepository;

    private final UserRepository userRepository;

    public AttendanceSession createSession(
            AttendanceSessionRequest request
    ) {

        AttendanceSession session =
                AttendanceSession.builder()
                        .title(request.getTitle())
                        .sessionDate(request.getSessionDate())
                        .attendanceCode(generateAttendanceCode())
                        .build();

        return sessionRepository.save(session);
    }

    public AttendanceRecord markAttendance(
            AttendanceRecordRequest request
    ) {

        User user =
                userRepository.findById(
                        request.getUserId()
                ).orElseThrow();

        AttendanceSession session =
                sessionRepository.findById(
                        request.getSessionId()
                ).orElseThrow();

        if (request.getAttendanceCode() == null
                || !request.getAttendanceCode().equals(session.getAttendanceCode())) {
            throw new BadRequestException("Invalid attendance code");
        }

        if (recordRepository.existsBySessionIdAndUserId(request.getSessionId(), request.getUserId())) {
            throw new BadRequestException("Attendance already marked for this member");
        }

        AttendanceRecord record =
                AttendanceRecord.builder()
                        .user(user)
                        .session(session)
                        .status(request.getStatus())
                        .build();

        return recordRepository.save(record);
    }

    public List<AttendanceSession> getSessions() {

        return sessionRepository.findAll();
    }

    public List<AttendanceRecord> getSessionAttendance(
            Long sessionId
    ) {

        return recordRepository.findBySessionId(sessionId);
    }

    public void deleteSession(Long id) {

        sessionRepository.deleteById(id);
    }

        public String getAttendanceCode(Long sessionId) {

                AttendanceSession session = sessionRepository.findById(sessionId).orElseThrow();
                return session.getAttendanceCode();
        }

        private String generateAttendanceCode() {

                return UUID.randomUUID()
                                .toString()
                                .replace("-", "")
                                .substring(0, 8)
                                .toUpperCase();
        }
}