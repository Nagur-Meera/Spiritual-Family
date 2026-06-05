// dto/event/EventResponse.java

package com.spiritualfamily.backend.dto.event;

import java.time.LocalDateTime;

import com.spiritualfamily.backend.entity.enums.EventType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponse {

    private Long id;
    private String title;
    private String description;
    private EventType eventType;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String venue;
    private Boolean onlineEvent;
}