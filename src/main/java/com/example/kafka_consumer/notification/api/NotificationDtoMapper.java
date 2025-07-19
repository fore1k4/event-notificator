package com.example.kafka_consumer.notification.api;

import com.example.kafka_consumer.notification.domain.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationDtoMapper {

    public NotificationDto toDto(Notification notification) {
        return new NotificationDto(
                notification.id(),
                notification.eventId(),
                notification.userId(),
                notification.createdAt(),
                notification.isRead()
        );
    }
}
