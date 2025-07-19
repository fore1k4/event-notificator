package com.example.kafka_consumer.notification.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
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
    private String oldName;
    private String newName;
    private Long oldMaxPlaces;
    private Long newMaxPlaces;
    private LocalDateTime oldDate;
    private LocalDateTime newDate;
    private Long oldCost;
    private Long newCost;
    private Long oldDuration;
    private Long newDuration;
    private Long oldLocationId;
    private Long newLocationId;
    private String oldEventStatus;
    private String newEventStatus;


    public NotificationEntity() {
    }

    public NotificationEntity(Long id, Long eventId, Long userId, ZonedDateTime createdAt, boolean isRead, String oldName, String newName, Long oldMaxPlaces, Long newMaxPlaces, LocalDateTime oldDate, LocalDateTime newDate, Long oldCost, Long newCost, Long oldDuration, Long newDuration, Long oldLocationId, Long newLocationId, String oldEventStatus, String newEventStatus) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.isRead = isRead;
        this.oldName = oldName;
        this.newName = newName;
        this.oldMaxPlaces = oldMaxPlaces;
        this.newMaxPlaces = newMaxPlaces;
        this.oldDate = oldDate;
        this.newDate = newDate;
        this.oldCost = oldCost;
        this.newCost = newCost;
        this.oldDuration = oldDuration;
        this.newDuration = newDuration;
        this.oldLocationId = oldLocationId;
        this.newLocationId = newLocationId;
        this.oldEventStatus = oldEventStatus;
        this.newEventStatus = newEventStatus;
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

//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public Long getOldMaxPlaces() {
        return oldMaxPlaces;
    }

    public void setOldMaxPlaces(Long oldMaxPlaces) {
        this.oldMaxPlaces = oldMaxPlaces;
    }

    public Long getNewMaxPlaces() {
        return newMaxPlaces;
    }

    public void setNewMaxPlaces(Long newMaxPlaces) {
        this.newMaxPlaces = newMaxPlaces;
    }

    public LocalDateTime getOldDate() {
        return oldDate;
    }

    public void setOldDate(LocalDateTime oldDate) {
        this.oldDate = oldDate;
    }

    public LocalDateTime getNewDate() {
        return newDate;
    }

    public void setNewDate(LocalDateTime newDate) {
        this.newDate = newDate;
    }

    public Long getOldCost() {
        return oldCost;
    }

    public void setOldCost(Long oldCost) {
        this.oldCost = oldCost;
    }

    public Long getNewCost() {
        return newCost;
    }

    public void setNewCost(Long newCost) {
        this.newCost = newCost;
    }

    public Long getOldDuration() {
        return oldDuration;
    }

    public void setOldDuration(Long oldDuration) {
        this.oldDuration = oldDuration;
    }

    public Long getNewDuration() {
        return newDuration;
    }

    public void setNewDuration(Long newDuration) {
        this.newDuration = newDuration;
    }

    public Long getOldLocationId() {
        return oldLocationId;
    }

    public void setOldLocationId(Long oldLocationId) {
        this.oldLocationId = oldLocationId;
    }

    public Long getNewLocationId() {
        return newLocationId;
    }

    public void setNewLocationId(Long newLocationId) {
        this.newLocationId = newLocationId;
    }

    public String getOldEventStatus() {
        return oldEventStatus;
    }

    public void setOldEventStatus(String oldEventStatus) {
        this.oldEventStatus = oldEventStatus;
    }

    public String getNewEventStatus() {
        return newEventStatus;
    }

    public void setNewEventStatus(String newEventStatus) {
        this.newEventStatus = newEventStatus;
    }
}
