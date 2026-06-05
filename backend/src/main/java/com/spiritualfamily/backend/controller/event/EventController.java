// controller/event/EventController.java

package com.spiritualfamily.backend.controller.event;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spiritualfamily.backend.dto.event.EventRequest;
import com.spiritualfamily.backend.dto.event.EventResponse;
import com.spiritualfamily.backend.service.event.EventService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@Tag(name = "Event APIs")
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(
            @RequestBody EventRequest request
    ) {

        return ResponseEntity.ok(
                eventService.createEvent(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>>
    getAllEvents() {

        return ResponseEntity.ok(
                eventService.getAllEvents()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse>
    getEventById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                eventService.getEventById(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(
            @PathVariable Long id
    ) {

        eventService.deleteEvent(id);

        return ResponseEntity.ok(
                "Event deleted successfully"
        );
    }
}