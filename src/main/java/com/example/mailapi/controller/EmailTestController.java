package com.example.mailapi.controller;

import com.example.mailapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class EmailTestController {

    private final EmailService emailService;

    public EmailTestController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    void test(){
        emailService.sendEmail(
                "ekamagerramov@gmail.com",
                "Test",
                "It's a test!"
                );
    }
}
