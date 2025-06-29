package com.example.kafka_consumer.notification.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.ZonedDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    List<NotificationEntity> findByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM NotificationEntity n WHERE n.createdAt < :threshold")
    Long deleteByCreateAtBefore(ZonedDateTime createAt);

    @Modifying
    @Query("""
       UPDATE NotificationEntity n
       SET n.isRead = true
       WHERE n.userId = :userId AND n.isRead = false
""")
    int markAllAsReadByUserId(@Param("userId") Long userId);
}
