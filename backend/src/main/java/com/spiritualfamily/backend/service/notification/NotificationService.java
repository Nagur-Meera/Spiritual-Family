package com.spiritualfamily.backend.service.notification;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spiritualfamily.backend.dto.notification.NotificationRequest;
import com.spiritualfamily.backend.entity.notification.Notification;
import com.spiritualfamily.backend.entity.user.User;
import com.spiritualfamily.backend.repository.notification.NotificationRepository;
import com.spiritualfamily.backend.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final UserRepository userRepository;

    public Notification createNotification(
            NotificationRequest request
    ) {

        User user =
                userRepository.findById(
                        request.getUserId()
                ).orElseThrow();

        Notification notification =
                Notification.builder()
                        .title(request.getTitle())
                        .message(request.getMessage())
                        .type(request.getType())
                        .user(user)
                        .build();

        return notificationRepository.save(notification);
    }

    public List<Notification> getUserNotifications(
            Long userId
    ) {

        return notificationRepository.findByUserId(userId);
    }

    public Notification markAsRead(
            Long id
    ) {

        Notification notification =
                notificationRepository.findById(id)
                        .orElseThrow();

        notification.setIsRead(true);

        return notificationRepository.save(notification);
    }

    public void deleteNotification(
            Long id
    ) {

        notificationRepository.deleteById(id);
    }
}