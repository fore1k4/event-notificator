package com.example.kafka_consumer.notification.entity;

import com.example.kafka_consumer.notification.NotificationType;
import com.example.kafka_consumer.notification.domain.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationEntityMapper {

    public NotificationEntity toEntity(Notification notification) {
        return null;
    }

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
