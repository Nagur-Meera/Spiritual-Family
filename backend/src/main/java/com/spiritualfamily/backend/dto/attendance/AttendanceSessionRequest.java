package com.spiritualfamily.backend.dto.attendance;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceSessionRequest {

    private String title;

    private LocalDate sessionDate;
}