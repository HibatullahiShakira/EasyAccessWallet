package com.semicolon.easyaccount.service;

import com.semicolon.easyaccount.Exceptions.WalletNotFoundException;
import com.semicolon.easyaccount.data.model.Wallet;
import com.semicolon.easyaccount.data.repository.WalletRepository;
import com.semicolon.easyaccount.dto.request.WalletDepositRequest;
import com.semicolon.easyaccount.dto.response.WalletDepositResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.semicolon.easyaccount.data.model.TransactionStatus.SUCCESS;

@Service
@AllArgsConstructor
public class AppWalletService implements WalletService {

    private final WalletRepository walletRepository;
    @Override
    public WalletDepositResponse deposit(WalletDepositRequest walletDepositRequest) throws WalletNotFoundException {
        Wallet wallet = walletRepository.findById(walletDepositRequest.getId())
                .orElseThrow(()-> new WalletNotFoundException("wallet not found with id %s", walletDepositRequest.getId()));
        String.format("Wallet found with id %s", wallet.getId());
        wallet.setBalance(wallet.getBalance().add(walletDepositRequest.getAmount()));
        walletRepository.save(wallet);
        WalletDepositResponse walletDepositResponse = new WalletDepositResponse();
        walletDepositResponse.setStatus(SUCCESS.toString());
        walletDepositResponse.setAmount(walletDepositRequest.getAmount());
        return walletDepositResponse;
        }
    }


