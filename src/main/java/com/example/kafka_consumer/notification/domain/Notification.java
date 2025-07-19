package com.example.kafka_consumer.notification.domain;

import java.time.ZonedDateTime;

public record Notification(
        Long id,

        Long eventId,

        Long userId,

        ZonedDateTime createdAt,

        boolean isRead
) {

}
