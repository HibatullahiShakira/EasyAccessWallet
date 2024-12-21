package com.semicolon.easyaccount.service;

import com.semicolon.easyaccount.Exceptions.WalletNotFoundException;
import com.semicolon.easyaccount.dto.request.WalletDepositRequest;
import com.semicolon.easyaccount.dto.response.WalletDepositResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class WalletServiceTest {

    @Autowired
    private WalletService walletService;

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void testThatWalletServiceCanDeposit() throws WalletNotFoundException {
        Long walletId = 201L;
        BigDecimal depositAmount = new BigDecimal("5000.00");
        WalletDepositRequest walletDepositRequest = new WalletDepositRequest();
        walletDepositRequest.setId(walletId);
        walletDepositRequest.setAmount(depositAmount);

        WalletDepositResponse walletDepositResponse = walletService.deposit(walletDepositRequest);
        assertNotNull(walletDepositResponse);
        assertNotNull(walletDepositResponse.getStatus());
        assertEquals("SUCCESS", walletDepositResponse.getStatus());

    }
}
