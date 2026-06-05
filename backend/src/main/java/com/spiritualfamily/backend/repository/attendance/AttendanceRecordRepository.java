package com.spiritualfamily.backend.repository.attendance;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.attendance.AttendanceRecord;

public interface AttendanceRecordRepository
        extends JpaRepository<AttendanceRecord, Long> {

    List<AttendanceRecord> findBySessionId(Long sessionId);

    Optional<AttendanceRecord> findBySessionIdAndUserId(Long sessionId, Long userId);

    boolean existsBySessionIdAndUserId(Long sessionId, Long userId);
}