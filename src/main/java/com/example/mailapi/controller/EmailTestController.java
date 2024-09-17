package com.example.mailapi.controller;

import com.example.mailapi.consumer.EmailConsumer;
import com.example.mailapi.controller.request.EmailRequest;
import com.example.mailapi.model.entity.Email;
import com.example.mailapi.service.EmailLogService;
import com.example.mailapi.service.EmailService;
import com.example.mailapi.utils.exception.EmailException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class EmailTestController {

    private final EmailService emailService;
    private final EmailLogService emailLogService;
    private final KafkaTemplate<String, EmailRequest> kafkaTemplate;

    public EmailTestController(EmailService emailService, EmailLogService emailLogService, EmailConsumer emailConsumer, KafkaTemplate<String, EmailRequest> kafkaTemplate) {
        this.emailService = emailService;
        this.emailLogService = emailLogService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    void test() throws EmailException {
        EmailRequest request = new EmailRequest();
        request.setSubject("Test");
        request.setRecipient("ekamagerramov@gmail.com");
        request.setBody("It's a test!");

        emailService.sendEmail(request);
    }

    @GetMapping("/all")
    List<Email> getAllTest() {
        return emailLogService.getAllEmails();
    }

    @PostMapping("/kafka/send-test")
    void testKafka() {
        EmailRequest request = new EmailRequest();
        request.setSender("TestSender@test.com");
        request.setSubject("Test");
        request.setRecipient("ekamagerramov@gmail.com");
        request.setBody("It's a test!");

        kafkaTemplate.send("email_topic", request);
    }
}
