package com.example.mailapi.model.entity;

import com.example.mailapi.utils.enums.EmailStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Email extends BaseEntity {
    private String sender;
    private String recipient;
    private String subject;
    @Lob
    private String body;
    @Enumerated(EnumType.STRING)
    private EmailStatus status;
}
