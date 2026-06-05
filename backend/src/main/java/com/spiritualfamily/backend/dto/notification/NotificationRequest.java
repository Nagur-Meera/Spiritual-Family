package com.spiritualfamily.backend.dto.notification;

import com.spiritualfamily.backend.entity.enums.NotificationType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {

    private String title;

    private String message;

    private NotificationType type;

    private Long userId;
}