package com.semicolon.easyaccount.service;


import com.semicolon.easyaccount.dto.request.TransactionRequest;
import com.semicolon.easyaccount.dto.response.TransactionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.semicolon.easyaccount.data.model.TransactionStatus.SUCCESS;
import static com.semicolon.easyaccount.dto.response.TransactionType.DEPOSIT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testThatUserCanMakeTransaction() {

        String transactionType = DEPOSIT.toString();
        String accountNumber = "1234567890";
        BigDecimal amount = new BigDecimal("5000.00");
        LocalDate transactionDate = LocalDate.of(2020, 1, 1);

        TransactionRequest transactionRequest = getTransactionRequest(accountNumber, amount, transactionType, transactionDate);
        TransactionResponse transactionResponse = transactionService.makeTransaction(transactionRequest);
        assertNotNull(transactionResponse);
        assertEquals(SUCCESS.toString(), transactionResponse.getStatus());
    }

    private TransactionRequest getTransactionRequest(String accountNumber, BigDecimal amount, String transactionType, LocalDate transactionDate) {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setReceiverAccountNumber(accountNumber);
        transactionRequest.setTransactionAmount(amount);
        transactionRequest.setTransactionDate(transactionDate);
        return transactionRequest;
    }


}
