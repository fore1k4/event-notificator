package com.example.kafka_consumer.Events;

import com.example.kafka_consumer.notification.NotificationType;
import com.example.kafka_consumer.notification.domain.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventKafkaListener {

    private final EventProcessor eventProcessor;

    @KafkaListener(
            topics = "event-topic",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(EventChangeMessage event, Acknowledgment ack) {
        if (event == null) {
            log.warn("Null ивент? Вы заебали.");
            return;
        }

        try {
            eventProcessor.processEvent(event);
            ack.acknowledge();
        } catch (Exception e) {
            log.error("Обосрался при обработке ивента: {}", event, e);
        }
    }
}
