package com.example.kafka_consumer.notification.api;

import java.util.List;

public record NotificationIdsDto(
        List<Long> notificationIds
) {
}
