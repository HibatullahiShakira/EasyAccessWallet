package com.semicolon.easyaccount.service;

import com.semicolon.easyaccount.Exceptions.CustomerEmailExist;
import com.semicolon.easyaccount.Exceptions.UserNotFoundExceptions;
import com.semicolon.easyaccount.Exceptions.WalletNotFoundException;
import com.semicolon.easyaccount.dto.request.DepositRequest;
import com.semicolon.easyaccount.dto.request.GetCustomerRequest;
import com.semicolon.easyaccount.dto.request.RegisterCustomerRequest;
import com.semicolon.easyaccount.dto.request.UpdateFormRequest;
import com.semicolon.easyaccount.dto.response.*;

public interface CustomerService {
    DepositResponse deposit(DepositRequest depositRequest) throws UserNotFoundExceptions, WalletNotFoundException;

    RegisterCustomerResponse registerCustomer(RegisterCustomerRequest registerCustomerRequest) throws CustomerEmailExist;

    GetCustomerResponse getCustomer(GetCustomerRequest getCustomerRequest) throws UserNotFoundExceptions;

    UpdateFormResponse updateCustomer(UpdateFormRequest updateFormRequest, Long customerId) throws UserNotFoundExceptions;

    DeleteCustomerResponse deleteCustomer(GetCustomerRequest getCustomerRequest) throws UserNotFoundExceptions;
}


