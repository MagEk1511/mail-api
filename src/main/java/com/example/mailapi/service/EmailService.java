package com.example.mailapi.service;

import com.example.mailapi.controller.request.EmailRequest;
import com.example.mailapi.utils.enums.EmailStatus;
import com.example.mailapi.utils.exception.EmailLogException;
import com.example.mailapi.utils.exception.EmailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender emailSender;
    private final EmailLogService emailLogService;

    public EmailService(JavaMailSender emailSender, EmailLogService emailLogService) {
        this.emailSender = emailSender;
        this.emailLogService = emailLogService;
    }

    public void sendEmail(EmailRequest emailRequest) throws EmailLogException, EmailSendException {
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
            throw new EmailSendException("Failed to send email: " + emailRequest, e);
        } finally {
            try {
                emailLogService.saveEmail(emailRequest, currentStatus);
            } catch (Exception logException) {
                throw new EmailLogException("Can't save email log for request: " + emailRequest, logException);
            }
        }
    }
}
