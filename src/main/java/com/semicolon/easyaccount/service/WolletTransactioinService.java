package com.semicolon.easyaccount.service;


import com.semicolon.easyaccount.data.model.Transaction;
import com.semicolon.easyaccount.data.repository.TransactionRepository;
import com.semicolon.easyaccount.dto.request.TransactionRequest;
import com.semicolon.easyaccount.dto.response.TransactionResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.semicolon.easyaccount.data.model.TransactionStatus.SUCCESS;

@Service
@AllArgsConstructor
public class WolletTransactioinService implements TransactionService {

    private final ModelMapper modelMapper;
    private TransactionRepository transactionRepository;

    @Override
    public TransactionResponse makeTransaction(TransactionRequest transactionRequest) {
        Transaction transactions = modelMapper.map(transactionRequest, Transaction.class);
        transactions = transactionRepository.save(transactions);

        TransactionResponse transactionResponse = modelMapper.map(transactions, TransactionResponse.class);
        transactionResponse.setStatus(SUCCESS.toString());

        return transactionResponse;
    }

    @Override
    public TransactionResponse getTransaction(Long transactionId) {
        return modelMapper.map(transactionRepository.findById(transactionId)
                .orElseThrow(()-> new RuntimeException(
                        "Transaction not found")), TransactionResponse.class
        );
    }
}
