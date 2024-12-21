package com.semicolon.easyaccount.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WalletDepositResponse {
    private String status;
    private BigDecimal amount;
}
