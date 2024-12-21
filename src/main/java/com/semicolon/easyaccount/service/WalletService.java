package com.semicolon.easyaccount.service;

import com.semicolon.easyaccount.Exceptions.WalletNotFoundException;
import com.semicolon.easyaccount.dto.request.WalletDepositRequest;
import com.semicolon.easyaccount.dto.response.WalletDepositResponse;

public interface WalletService {

    WalletDepositResponse deposit(WalletDepositRequest walletDepositRequest) throws WalletNotFoundException;

}

