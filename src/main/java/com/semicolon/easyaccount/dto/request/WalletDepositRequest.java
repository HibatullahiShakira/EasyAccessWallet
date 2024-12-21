package com.semicolon.easyaccount.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class WalletDepositRequest {
    private Long id;
    private BigDecimal amount;
}
