package com.example.kafka_consumer.notification;

import com.example.kafka_consumer.notification.entity.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class NotificationTransactionalService {

    private final NotificationRepository notificationRepository;

    public NotificationTransactionalService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public void markAllAsReadByUserId(Long userId) {
        notificationRepository.markAllAsReadByUserId(userId);
    }
}