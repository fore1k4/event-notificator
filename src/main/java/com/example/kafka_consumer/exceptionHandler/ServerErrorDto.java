package com.example.kafka_consumer.exceptionHandler;

import java.time.LocalDateTime;

public record ServerErrorDto (
        String message,
        String detailedMessage,
        LocalDateTime time
)
{
}
