// entity/event/EventRSVP.java

package com.spiritualfamily.backend.entity.event;

import java.time.LocalDateTime;

import com.spiritualfamily.backend.entity.enums.RsvpStatus;
import com.spiritualfamily.backend.entity.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "event_rsvps")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRSVP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private RsvpStatus status;

    @Builder.Default
    private LocalDateTime respondedAt =
            LocalDateTime.now();
}