package com.example.kafka_consumer.notification.schedule;

import com.example.kafka_consumer.notification.entity.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Slf4j
@Service
public class NotificationCleanService {

    private final NotificationRepository notificationRepository;

    public NotificationCleanService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Scheduled(cron = "0 0 3 * * *")
    public void cleanNotifications() {
        ZonedDateTime date = ZonedDateTime.now().minusDays(7);
        Long deletedDates = notificationRepository.deleteByCreateAtBefore(date);
        log.info("Clean dates: {}", deletedDates);
    }


}
