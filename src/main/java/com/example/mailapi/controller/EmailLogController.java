package com.example.mailapi.controller;

import com.example.mailapi.model.entity.Email;
import com.example.mailapi.service.EmailLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emails")
public class EmailLogController {


    private final EmailLogService emailLogService;

    public EmailLogController(EmailLogService emailLogService) {
        this.emailLogService = emailLogService;
    }

    @GetMapping(params = "id")
    public Optional<Email> email(@RequestParam Long id) {
        return emailLogService.getEmailById(id);
    }

    @GetMapping
    public List<Email> emails() {
        return emailLogService.getAllEmails();
    }

}
