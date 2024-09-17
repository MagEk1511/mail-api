package com.example.mailapi;

import com.example.mailapi.controller.request.EmailRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = { "email_topic" })
public class KafkaTest {

    @Autowired
    private KafkaTemplate<String, EmailRequest> kafkaTemplate;


    @Test
    public void testEmailSendToKafka() {
        EmailRequest testEmailRequest = new EmailRequest();
        testEmailRequest.setBody("Test broker!");
        testEmailRequest.setSubject("Kafka test");
        testEmailRequest.setSender("Kafka@test.com");
        testEmailRequest.setRecipient("ekamagerramov@gmail.com");

        kafkaTemplate.send("email-topic", testEmailRequest);
    }
}
