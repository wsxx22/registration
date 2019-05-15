package com.example.registration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "activation_tokens")
public class ActivationToken extends AbstractEntity {

    @PrePersist
    private void prePersist() {
        value = UUID.randomUUID().toString().replace("-", "");
        expiresAt = LocalDateTime.now().plusDays(7);
    }

    private String value;

    private LocalDateTime expiresAt;



}
