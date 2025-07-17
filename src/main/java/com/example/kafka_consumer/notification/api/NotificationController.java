package com.example.kafka_consumer.notification.api;

import com.example.kafka_consumer.notification.domain.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationDtoMapper notificationDtoMapper;
    private final NotificationService notificationService;

    public NotificationController(
            NotificationDtoMapper notificationDtoMapper,
            NotificationService notificationService
    ) {
        this.notificationDtoMapper = notificationDtoMapper;
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<NotificationDto>> getNotifications() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof Long userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var notifications = notificationService.getNotificationsByUserId(userId)
                .stream()
                .map(notificationDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(notifications);
    }

    @PostMapping
    public ResponseEntity<Void> makeNotificationsIsRead(
       @RequestBody NotificationIdsDto notificationIdsDto
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof Long userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        notificationService.makeNotificationsIsRead(userId, notificationIdsDto.notificationIds());

        return ResponseEntity.ok().build();
    }

}
