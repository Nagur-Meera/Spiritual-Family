// repository/event/EventRepository.java

package com.spiritualfamily.backend.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.event.Event;

public interface EventRepository
        extends JpaRepository<Event, Long> {
}