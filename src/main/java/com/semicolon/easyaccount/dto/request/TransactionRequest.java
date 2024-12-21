package com.semicolon.easyaccount.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionRequest {
    private String transactionStatus;
    private BigDecimal transactionAmount;
    private LocalDate transactionDate;
    private String description;
    private String senderAccountNumber;
    private String receiverAccountNumber;
}
