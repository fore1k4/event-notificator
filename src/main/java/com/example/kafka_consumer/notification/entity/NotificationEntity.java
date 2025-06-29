package com.example.kafka_consumer.notification.entity;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "notification")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "date")
    private ZonedDateTime createdAt;

    @Column(name = "is_read")
    private boolean isRead;

    @Column(name = "type")
    private String type;

    public NotificationEntity() {
    }

    public NotificationEntity(
            Long id,
            Long eventId,
            Long userId,
            ZonedDateTime createdAt,
            boolean isRead,
            String type
    ) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.isRead = isRead;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
