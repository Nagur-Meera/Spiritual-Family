// ======================================================
// entity/user/MemberProfile.java
// ======================================================

package com.spiritualfamily.backend.entity.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "member_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gender;

    private LocalDate dateOfBirth;

    @Column(columnDefinition = "TEXT")
    private String address;

    private String preferredLanguage;

    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "member_prayer_interests",

            joinColumns = @JoinColumn(name = "member_profile_id"),

            inverseJoinColumns = @JoinColumn(name = "prayer_interest_id")
    )
    private Set<PrayerInterest> prayerInterests = new HashSet<>();

    @PrePersist
    public void prePersist() {

        this.createdAt = LocalDateTime.now();
    }
}