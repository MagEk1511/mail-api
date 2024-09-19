package com.example.mailapi.controller;

import com.example.mailapi.config.KafkaProducerConfig;
import com.example.mailapi.controller.request.EmailRequest;
import com.example.mailapi.service.EmailService;
import com.example.mailapi.utils.exception.EmailLogException;
import com.example.mailapi.utils.exception.EmailSendException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final KafkaTemplate<String, EmailRequest> kafkaTemplate;
    private final EmailService emailService;

    public EmailController(KafkaTemplate<String, EmailRequest> kafkaTemplate, EmailService emailService) {
        this.kafkaTemplate = kafkaTemplate;
        this.emailService = emailService;
    }

    @PostMapping("/kafka/send")
    public void sendToTopic(@RequestBody EmailRequest request) {
        kafkaTemplate.send(KafkaProducerConfig.EMAIL_TOPIC, request);
    }

    @PostMapping("/send")
    public void send(@RequestBody EmailRequest request) throws EmailSendException, EmailLogException {
        emailService.sendEmail(request);
    }
}
