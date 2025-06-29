package com.example.kafka_consumer.notification.api;

import com.example.kafka_consumer.notification.NotificationType;

import java.time.ZonedDateTime;

public record NotificationDto(
        Long id,

        Long eventId,

        Long userId,

        ZonedDateTime createdAt,

        boolean isRead,

        NotificationType type
) {
}
