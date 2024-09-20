package com.example.mailapi.service;

import com.example.mailapi.controller.request.EmailRequest;
import com.example.mailapi.model.entity.Email;
import com.example.mailapi.model.repository.EmailRepository;
import com.example.mailapi.utils.enums.EmailStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailLogService {

    @Autowired
    private EmailRepository emailRepository;

    void saveEmail(EmailRequest emailRequest, EmailStatus status) {
        Email email = new Email();

        email.setSender(email.getSender());
        email.setRecipient(emailRequest.getRecipient());
        email.setSubject(emailRequest.getSubject());
        email.setBody(emailRequest.getBody());
        email.setStatus(status);

        emailRepository.save(email);
    }

    public Optional<Email> getEmailById(Long id) {
        return emailRepository.findById(id);
    }

    public List<Email> getAllEmails() {
        return emailRepository.findAll();
    }
}
