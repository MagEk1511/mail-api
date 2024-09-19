package com.example.mailapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

public class ErrorHandlerConfig {

    @Bean
    DefaultErrorHandler eh() {
        return new DefaultErrorHandler(new FixedBackOff(0, 0));
    }
}
