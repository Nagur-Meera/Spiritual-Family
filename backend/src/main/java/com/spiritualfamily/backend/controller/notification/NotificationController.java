package com.spiritualfamily.backend.controller.notification;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.access.prepost.PreAuthorize;

import com.spiritualfamily.backend.dto.notification.NotificationRequest;
import com.spiritualfamily.backend.entity.notification.Notification;
import com.spiritualfamily.backend.service.notification.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class NotificationController {

    private final NotificationService service;

    @PostMapping
    public Notification createNotification(
            @RequestBody NotificationRequest request
    ) {

        return service.createNotification(request);
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getUserNotifications(
            @PathVariable Long userId
    ) {

        return service.getUserNotifications(userId);
    }

    @PutMapping("/{id}/read")
    public Notification markAsRead(
            @PathVariable Long id
    ) {

        return service.markAsRead(id);
    }

    @DeleteMapping("/{id}")
    public String deleteNotification(
            @PathVariable Long id
    ) {

        service.deleteNotification(id);

        return "Notification Deleted";
    }
}