package com.spiritualfamily.backend.controller.attendance;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spiritualfamily.backend.dto.attendance.AttendanceRecordRequest;
import com.spiritualfamily.backend.dto.attendance.AttendanceSessionRequest;
import com.spiritualfamily.backend.entity.attendance.AttendanceRecord;
import com.spiritualfamily.backend.entity.attendance.AttendanceSession;
import com.spiritualfamily.backend.service.attendance.AttendanceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService service;

    @PostMapping("/sessions")
    public AttendanceSession createSession(
            @RequestBody AttendanceSessionRequest request
    ) {

        return service.createSession(request);
    }

    @PostMapping("/mark")
    public AttendanceRecord markAttendance(
            @RequestBody AttendanceRecordRequest request
    ) {

        return service.markAttendance(request);
    }

    @GetMapping("/sessions")
    public List<AttendanceSession> getSessions() {

        return service.getSessions();
    }

    @GetMapping("/session/{sessionId}")
    public List<AttendanceRecord> getSessionAttendance(
            @PathVariable Long sessionId
    ) {

        return service.getSessionAttendance(sessionId);
    }

    @GetMapping("/sessions/{sessionId}/code")
    public String getAttendanceCode(@PathVariable Long sessionId) {

        return service.getAttendanceCode(sessionId);
    }

    @DeleteMapping("/sessions/{id}")
    public String deleteSession(
            @PathVariable Long id
    ) {

        service.deleteSession(id);

        return "Attendance Session Deleted";
    }
}