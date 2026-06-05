// service/event/EventService.java

package com.spiritualfamily.backend.service.event;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spiritualfamily.backend.dto.event.EventRequest;
import com.spiritualfamily.backend.dto.event.EventResponse;
import com.spiritualfamily.backend.entity.event.Event;
import com.spiritualfamily.backend.repository.event.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public EventResponse createEvent(
            EventRequest request
    ) {

        Event event = Event.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .eventType(request.getEventType())
                .startDateTime(request.getStartDateTime())
                .endDateTime(request.getEndDateTime())
                .venue(request.getVenue())
                .meetingLink(request.getMeetingLink())
                .mapsLink(request.getMapsLink())
                .onlineEvent(request.getOnlineEvent())
                .reminderMinutesBefore(
                        request.getReminderMinutesBefore()
                )
                .build();

        Event saved =
                eventRepository.save(event);

        return map(saved);
    }

    public List<EventResponse> getAllEvents() {

        return eventRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    public EventResponse getEventById(Long id) {

        Event event = eventRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Event not found"
                        )
                );

        return map(event);
    }

    public void deleteEvent(Long id) {

        eventRepository.deleteById(id);
    }

    private EventResponse map(Event event) {

        return EventResponse.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .eventType(event.getEventType())
                .startDateTime(event.getStartDateTime())
                .endDateTime(event.getEndDateTime())
                .venue(event.getVenue())
                .onlineEvent(event.getOnlineEvent())
                .build();
    }
}