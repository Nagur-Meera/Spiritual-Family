package com.spiritualfamily.backend.repository.notification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.notification.Notification;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    List<Notification> findByUserId(Long userId);
}