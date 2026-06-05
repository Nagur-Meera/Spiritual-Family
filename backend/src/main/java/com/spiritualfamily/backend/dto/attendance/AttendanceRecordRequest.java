package com.spiritualfamily.backend.dto.attendance;

import com.spiritualfamily.backend.entity.enums.AttendanceStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRecordRequest {

    private Long userId;

    private Long sessionId;

    private String attendanceCode;

    private AttendanceStatus status;
}