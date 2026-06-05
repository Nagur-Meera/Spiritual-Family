package com.spiritualfamily.backend.repository.attendance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.attendance.AttendanceSession;

public interface AttendanceSessionRepository
        extends JpaRepository<AttendanceSession, Long> {

        Optional<AttendanceSession> findByAttendanceCode(String attendanceCode);
}