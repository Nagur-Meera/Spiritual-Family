// dto/event/EventRequest.java

package com.spiritualfamily.backend.dto.event;

import com.spiritualfamily.backend.entity.enums.EventType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {

    private String title;
    private String description;
    private EventType eventType;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String venue;
    private String meetingLink;
    private String mapsLink;
    private Boolean onlineEvent;
    private Integer reminderMinutesBefore;
}