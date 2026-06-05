package com.spiritualfamily.backend.entity.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prayer_interests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrayerInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}
