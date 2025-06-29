package com.example.kafka_consumer.Events;

import com.example.kafka_consumer.notification.NotificationType;
import com.example.kafka_consumer.notification.domain.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventProcessor {

    private final NotificationService notificationService;

    @Transactional
    public void processEvent(EventChangeMessage event) {
        log.info("⚙️ Обработка ивента: {}", event.eventId());

        List<String> changedFields = getChangedFields(event);

        if (changedFields.isEmpty()) {
            log.info("В ивенте {} ничего не поменялось.", event.eventId());
            return;
        }

        String notificationText = buildNotificationText(event, changedFields);

        notificationService.createNotification(
                event.eventId(),
                event.users(),
                NotificationType.UPDATED
        );

        log.info("📣 Отправили уведомление: {}", notificationText);
    }

    private boolean isFieldChanged(EventFieldChange<?> field) {
        if (field == null) return false;

        Object oldF = field.getOldField();
        Object newF = field.getNewField();

        log.info("Сравниваем поле: старое = {}, новое = {}", oldF, newF);

        if (oldF == null && newF == null) return false;

        if (oldF == null || newF == null) return true;

        if (oldF instanceof java.time.temporal.Temporal && newF instanceof java.time.temporal.Temporal) {
            return !oldF.equals(newF);
        }

        return !oldF.equals(newF);
    }

    private List<String> getChangedFields(EventChangeMessage event) {
        List<String> changedFields = new ArrayList<>();

        if (isFieldChanged(event.name())) changedFields.add("name");
        if (isFieldChanged(event.cost())) changedFields.add("cost");
        if (isFieldChanged(event.date())) changedFields.add("date");
        if (isFieldChanged(event.locationId())) changedFields.add("locationId");
        if (isFieldChanged(event.duration())) changedFields.add("duration");
        if (isFieldChanged(event.maxPlaces())) changedFields.add("maxPlaces");
        if (isFieldChanged(event.status())) changedFields.add("status");

        return changedFields;
    }


    private String buildNotificationText(EventChangeMessage event, List<String> fields) {
        StringBuilder text = new StringBuilder("🔄 Ивент изменился:\n");

        for (String field : fields) {
            switch (field) {
                case "name" -> appendField(text, "Имя", event.name());
                case "cost" -> appendField(text, "Стоимость", event.cost());
                case "date" -> appendField(text, "Дата", event.date());
                case "locationId" -> appendField(text, "Локация", event.locationId());
                case "duration" -> appendField(text, "Длительность", event.duration());
                case "maxPlaces" -> appendField(text, "Макс. мест", event.maxPlaces());
                case "status" -> appendField(text, "Статус", event.status());
            }
        }

        return text.toString();
    }

    private <T> void appendField(StringBuilder sb, String name, EventFieldChange<T> field) {
        if (field != null) {
            sb.append(String.format("%s: %s → %s%n", name, field.getOldField(), field.getNewField()));
        }
    }
}