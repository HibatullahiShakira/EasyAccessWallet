package com.semicolon.easyaccount.service;

import com.semicolon.easyaccount.dto.request.TransactionRequest;
import com.semicolon.easyaccount.dto.response.TransactionResponse;

public interface TransactionService {
    TransactionResponse makeTransaction(TransactionRequest transactionRequest);

    TransactionResponse getTransaction(Long transactionId);
}
