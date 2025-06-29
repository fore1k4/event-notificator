package com.example.kafka_consumer.notification.domain;

import com.example.kafka_consumer.notification.NotificationTransactionalService;
import com.example.kafka_consumer.notification.NotificationType;
import com.example.kafka_consumer.notification.entity.NotificationEntity;
import com.example.kafka_consumer.notification.entity.NotificationEntityMapper;
import com.example.kafka_consumer.notification.entity.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationEntityMapper notificationEntityMapper;

    private final NotificationTransactionalService notificationTransactionalService;

    public NotificationService(
            NotificationRepository notificationRepository,
            NotificationEntityMapper notificationEntityMapper, NotificationTransactionalService notificationTransactionalService
    ) {
        this.notificationRepository = notificationRepository;
        this.notificationEntityMapper = notificationEntityMapper;
        this.notificationTransactionalService = notificationTransactionalService;
    }

    public void createNotification(
          Long eventId,
          List<Long> usersId,
          NotificationType notificationType
    ) {


        List<NotificationEntity> notificationEntityList = usersId.stream()
                .map(userId -> new NotificationEntity(
                        null,
                        eventId,
                        userId,
                        ZonedDateTime.now(),
                        false,
                        notificationType.name()
                ))
                .toList();

        notificationRepository.saveAll(notificationEntityList);
    }


    public List<Notification> getNotificationsByUserId(Long userId) {
        var entities = notificationRepository.findByUserId(userId);

        notificationTransactionalService.markAllAsReadByUserId(userId);

        return entities.stream()
                .map(notificationEntityMapper::toDomain)
                .toList();
    }



}
