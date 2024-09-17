package com.example.mailapi.utils.exception;

public class EmailLogException extends EmailException {
    public EmailLogException(String s, Exception e) {
        super(s, e);
    }
}
