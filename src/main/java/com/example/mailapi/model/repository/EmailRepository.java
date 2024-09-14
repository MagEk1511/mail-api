package com.example.mailapi.model.repository;

import com.example.mailapi.model.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
    
}
