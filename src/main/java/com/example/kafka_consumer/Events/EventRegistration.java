package com.example.kafka_consumer.Events;


public record EventRegistration(
        Long id,
        Long userId,
        Long eventId
) {

}
