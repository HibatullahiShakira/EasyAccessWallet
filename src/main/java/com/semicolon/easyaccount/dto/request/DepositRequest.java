package com.semicolon.easyaccount.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class DepositRequest {
    private Long customerId;
    private String description;
    private BigDecimal amount;
    private String receiverAccountNumber;

}
