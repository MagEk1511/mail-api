package com.example.mailapi.utils.exception;

public class EmailSendException extends EmailException {
    public EmailSendException(String s, Exception e) {
        super(s, e);
    }
}
