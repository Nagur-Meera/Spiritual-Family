// repository/event/EventRSVPRepository.java

package com.spiritualfamily.backend.repository.event;

import com.spiritualfamily.backend.entity.event.EventRSVP;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRSVPRepository
        extends JpaRepository<EventRSVP, Long> {
}