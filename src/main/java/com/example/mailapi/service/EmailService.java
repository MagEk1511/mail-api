package com.example.mailapi.service;

import com.example.mailapi.controller.request.EmailRequest;
import com.example.mailapi.utils.enums.EmailStatus;
import com.example.mailapi.utils.exception.EmailLogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender emailSender;
    @Autowired
    private EmailLogService emailLogService;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(EmailRequest emailRequest) throws EmailLogException {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailRequest.getSender());
        message.setTo(emailRequest.getRecipient());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getBody());

        EmailStatus currentStatus = EmailStatus.PENDING;

        try {
            emailSender.send(message);
            currentStatus = EmailStatus.SENT;
        } catch (Exception e) {
            currentStatus = EmailStatus.FAILED;
            throw e;
        } finally {
            try {
                emailLogService.saveEmail(emailRequest, currentStatus);
            } catch (Exception e) {
                throw new EmailLogException("Can't save" + emailRequest);
            }
        }
    }
}
