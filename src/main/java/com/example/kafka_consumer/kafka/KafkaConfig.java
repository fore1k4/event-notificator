package com.example.kafka_consumer.kafka;

import com.example.kafka_consumer.events.EventChangeMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public ConsumerFactory<Long, EventChangeMessage> consumerFactory() {
        JsonDeserializer<EventChangeMessage> delegate = new JsonDeserializer<>(EventChangeMessage.class, false);
        delegate.addTrustedPackages("*");

        ErrorHandlingDeserializer<EventChangeMessage> errorHandlingDeserializer = new ErrorHandlingDeserializer<>(delegate);

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "notificator-group");

        return new DefaultKafkaConsumerFactory<>(props, new LongDeserializer(), errorHandlingDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, EventChangeMessage> kafkaListenerContainerFactory(
            ConsumerFactory<Long, EventChangeMessage> consumerFactory
    ) {
        var factory = new ConcurrentKafkaListenerContainerFactory<Long, EventChangeMessage>();
        factory.setConsumerFactory(consumerFactory);


        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);


        factory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(1000L, 3)));

        return factory;
    }
}
