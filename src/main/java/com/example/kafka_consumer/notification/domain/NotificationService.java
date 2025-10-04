package com.example.kafka_consumer.notification.domain;

import com.example.kafka_consumer.events.EventChangeMessage;
import com.example.kafka_consumer.notification.NotificationType;
import com.example.kafka_consumer.notification.entity.NotificationEntity;
import com.example.kafka_consumer.notification.entity.NotificationEntityMapper;
import com.example.kafka_consumer.notification.entity.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationEntityMapper notificationEntityMapper;


    public NotificationService(
            NotificationRepository notificationRepository,
            NotificationEntityMapper notificationEntityMapper
    ) {
        this.notificationRepository = notificationRepository;
        this.notificationEntityMapper = notificationEntityMapper;
    }

    public void createNotification(
            Long eventId,
            List<Long> usersId,
            EventChangeMessage event,
            NotificationType notificationType
    ) {


        List<NotificationEntity> notificationEntityList = usersId.stream()
                .map(userId -> new NotificationEntity(
                        null,
                        eventId,
                        userId,
                        ZonedDateTime.now(),
                        false,
                        event.name().getOldField(),
                        event.name().getNewField(),
                        event.maxPlaces().getOldField(),
                        event.maxPlaces().getNewField(),
                        event.date().getOldField().toLocalDateTime(),
                        event.date().getNewField().toLocalDateTime(),
                        event.cost().getOldField(),
                        event.cost().getNewField(),
                        event.duration().getOldField(),
                        event.duration().getNewField(),
                        event.locationId().getOldField(),
                        event.locationId().getNewField(),
                        event.status().getOldField().name(),
                        event.status().getNewField().name()
                ))
                .toList();

        notificationRepository.saveAll(notificationEntityList);
    }


    public List<Notification> getNotificationsByUserId(Long userId) {
        var entities = notificationRepository.findByUserId(userId);


        return entities.stream()
                .map(notificationEntityMapper::toDomain)
                .toList();
    }

    @Transactional
    public void makeNotificationsIsRead(Long userId, List<Long> notificationIds) {
        notificationRepository.markNotificationAsRead(userId, notificationIds);
    }


}
