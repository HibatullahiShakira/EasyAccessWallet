package com.semicolon.easyaccount.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
public class Transaction {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private String message;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private TransactionStatus status;
}
