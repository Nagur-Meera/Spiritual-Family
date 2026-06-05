// repository/event/EventReminderRepository.java

package com.spiritualfamily.backend.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.event.EventReminder;

public interface EventReminderRepository
        extends JpaRepository<EventReminder, Long> {
}