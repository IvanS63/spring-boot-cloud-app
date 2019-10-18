package com.myapp.bookstore.config;

import com.myapp.kafkaservice.dto.SendRequestDto;
import com.myapp.kafkaservice.util.AuthorRequestSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * KafkaTestConig.
 *
 * @author Ivan_Semenov
 */
@TestConfiguration
@ConditionalOnProperty("kafka.enabled")
public class KafkaTestConfig {

    @Value("${kafka.bootstrap-servers}")
    private String servers;

    @Bean
    public ProducerFactory<String, SendRequestDto> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AuthorRequestSerializer.class);
        configProps.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, "60000");//message timeout
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, SendRequestDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}

