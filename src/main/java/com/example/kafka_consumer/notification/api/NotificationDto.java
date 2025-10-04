package com.example.kafka_consumer.notification.api;

import java.time.ZonedDateTime;

public record NotificationDto(
        Long id,

        Long eventId,

        Long userId,

        ZonedDateTime createdAt,

        boolean isRead
) {
}
