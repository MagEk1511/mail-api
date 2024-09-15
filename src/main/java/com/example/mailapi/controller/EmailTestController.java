package com.example.mailapi.controller;

import com.example.mailapi.controller.request.EmailRequest;
import com.example.mailapi.model.entity.Email;
import com.example.mailapi.service.EmailLogService;
import com.example.mailapi.service.EmailService;
import com.example.mailapi.utils.exception.EmailLogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class EmailTestController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private EmailLogService emailLogService;

    @PostMapping
    void test() throws EmailLogException {
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
}
