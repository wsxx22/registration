package com.example.registration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "activation_tokens")
public class ActivationToken extends AbstractEntity{

    private String value;

    private LocalDateTime creationDate;

    private LocalDateTime expirationDate;



}
