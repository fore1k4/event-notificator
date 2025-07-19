package com.example.kafka_consumer.notification.entity;

import com.example.kafka_consumer.notification.domain.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationEntityMapper {
    public Notification toDomain(NotificationEntity notificationEntity) {
        return new Notification(
                notificationEntity.getId(),
                notificationEntity.getEventId(),
                notificationEntity.getUserId(),
                notificationEntity.getCreatedAt(),
                notificationEntity.isRead()
        );
    }
}
