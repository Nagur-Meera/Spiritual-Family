package com.spiritualfamily.backend.entity.attendance;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "attendance_sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate sessionDate;

    @Column(unique = true)
    private String attendanceCode;

    @OneToMany(mappedBy = "session",
            cascade = CascadeType.ALL)
    private List<AttendanceRecord> records;
}