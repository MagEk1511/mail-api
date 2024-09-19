package com.example.mailapi.consumer;

import com.example.mailapi.config.KafkaProducerConfig;
import com.example.mailapi.controller.request.EmailRequest;
import com.example.mailapi.service.EmailService;
import com.example.mailapi.utils.exception.EmailException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = KafkaProducerConfig.EMAIL_TOPIC, groupId = "email-service-group")
    public void consumeEmailRequest(@Payload EmailRequest emailRequest) throws EmailException {
        emailService.sendEmail(emailRequest);
    }
}
