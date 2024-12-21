package com.semicolon.easyaccount.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepositResponse {
    private String message;
    private String status;
    private Long transactionId;
    private String amount;
}
