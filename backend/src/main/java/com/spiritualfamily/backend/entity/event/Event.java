// entity/event/Event.java

package com.spiritualfamily.backend.entity.event;

import com.spiritualfamily.backend.entity.enums.EventType;
import com.spiritualfamily.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 5000)
    private String description;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String venue;

    private String meetingLink;

    private String mapsLink;

    private Boolean onlineEvent;

    private Integer reminderMinutesBefore;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Builder.Default
    private Boolean active = true;

    @Builder.Default
    private LocalDateTime createdAt =
            LocalDateTime.now();

    @OneToMany(mappedBy = "event",
            cascade = CascadeType.ALL)
    private List<EventRSVP> rsvps;
}