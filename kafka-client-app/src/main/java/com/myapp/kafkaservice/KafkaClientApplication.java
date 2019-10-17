package com.myapp.kafkaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * KafkaClientApplication.
 *
 * @author Ivan_Semenov
 */
@SpringBootApplication
@ComponentScan({"com.myapp.kafkaservice"})
public class KafkaClientApplication {

    /**
     * To test go to:
     * http://localhost:8092/kafka-app/send-create-message  - for sending CREATE Kafka request.
     * http://localhost:8092/kafka-app/send-delete-message  - for sending DELETE Kafka request.
     */
    public static void main(String[] args) {
        SpringApplication.run(KafkaClientApplication.class, args);
    }
}
