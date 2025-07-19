package com.example.kafka_consumer.events;

import java.time.ZonedDateTime;
import java.util.List;

public record EventChangeMessage(
        Long eventId,

        Long changerId,

        Long ownerId,

        String token,

        List<Long> users,

        EventFieldChange<String> name,

        EventFieldChange<Long> maxPlaces,

        EventFieldChange<ZonedDateTime> date,

        EventFieldChange<Long> cost,

        EventFieldChange<Long> duration,

        EventFieldChange<Long> locationId,

        EventFieldChange<EventStatus> status

) {

}
