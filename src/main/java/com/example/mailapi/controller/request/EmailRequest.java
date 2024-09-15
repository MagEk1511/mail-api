package com.example.mailapi.controller.request;

import lombok.Data;

@Data
public class EmailRequest {
    private String sender;
    private String recipient;
    private String subject;
    private String body;
}
