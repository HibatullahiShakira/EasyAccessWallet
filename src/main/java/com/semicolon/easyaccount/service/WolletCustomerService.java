package com.semicolon.easyaccount.service;

import com.semicolon.easyaccount.Exceptions.CustomerEmailExist;
import com.semicolon.easyaccount.Exceptions.UserNotFoundExceptions;
import com.semicolon.easyaccount.Exceptions.WalletNotFoundException;
import com.semicolon.easyaccount.data.model.Customer;
import com.semicolon.easyaccount.data.model.Wallet;
import com.semicolon.easyaccount.data.repository.CustomerRepository;
import com.semicolon.easyaccount.dto.request.*;
import com.semicolon.easyaccount.dto.response.*;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class WolletCustomerService implements CustomerService {

    private final CustomerRepository customerRepository;
    private final WalletService walletService;
    private final ModelMapper modelMapper;
    private final TransactionService transactionService;


    @Override
    public DepositResponse deposit(DepositRequest depositRequest) throws UserNotFoundExceptions, WalletNotFoundException {
        Customer customer = getCustomerFor(depositRequest.getCustomerId());
        Wallet customerWallet = customer.getWallet();
        WalletDepositRequest walletDepositRequest =  createWalletDepositRequest(depositRequest, customerWallet);
        WalletDepositResponse response = walletService.deposit(walletDepositRequest);
        return createCustomerDepositResponse(depositRequest, response, customerWallet);
    }

    @Override
    public RegisterCustomerResponse registerCustomer(RegisterCustomerRequest registerCustomerRequest) throws CustomerEmailExist {
        Customer customer = modelMapper.map(registerCustomerRequest, Customer.class);
        customer = customerRepository.save(customer);
        return customerRegistrationResponse(customer);
    }

    @Override
    public GetCustomerResponse getCustomer(GetCustomerRequest getCustomerRequest) throws UserNotFoundExceptions {
        Customer customer = getCustomerFor(getCustomerRequest.getCustomerId());
        return modelMapper.map(customer, GetCustomerResponse.class);
    }

    @Override
    public UpdateFormResponse updateCustomer(UpdateFormRequest updateFormRequest, Long customerId) throws UserNotFoundExceptions {
        Customer customer = getCustomerFor(customerId);
        modelMapper.map(customer, updateFormRequest);
        customer = customerRepository.save(customer);
        UpdateFormResponse response = new UpdateFormResponse();
        response.setMessage("Customer details update successfully");
        return response;
    }

    @Override
    public DeleteCustomerResponse deleteCustomer(GetCustomerRequest getCustomerRequest) throws UserNotFoundExceptions {
        Customer customer = getCustomerFor(getCustomerRequest.getCustomerId());
        customerRepository.delete(customer);
        DeleteCustomerResponse response = new DeleteCustomerResponse();
        response.setMessage("Customer delete successfully");
        return response;
    }

    private static RegisterCustomerResponse customerRegistrationResponse(Customer customer) {
        RegisterCustomerResponse registerCustomerResponse = new RegisterCustomerResponse();
        registerCustomerResponse.setCustomerId(customer.getId());
        registerCustomerResponse.setMessage("Customer successfully registered");
        return registerCustomerResponse;
    }

    private Customer getCustomerFor(Long id) throws UserNotFoundExceptions {
        return customerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundExceptions(
                        String.format("Customer with the id %d not found", id)));
    }

    private DepositResponse createCustomerDepositResponse(DepositRequest depositRequest, WalletDepositResponse response, Wallet customerWallet) {
        DepositResponse depositResponse = new DepositResponse();
        depositResponse.setMessage(response.getStatus());
        depositResponse.setStatus(response.getStatus());
        depositResponse.setAmount(depositResponse.getAmount());
        TransactionRequest transactionRequest = createTransactionRequest(depositRequest, depositResponse, customerWallet);
        TransactionResponse transactionResponse = transactionService.makeTransaction(transactionRequest);
        depositResponse.setTransactionId(transactionResponse.getId());
        return depositResponse;
    }

    private static TransactionRequest createTransactionRequest(DepositRequest depositRequest, DepositResponse depositResponse, Wallet customerWallet) {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setTransactionDate(LocalDate.now());
        transactionRequest.setTransactionAmount(depositRequest.getAmount());
        transactionRequest.setDescription(depositRequest.getDescription());
        transactionRequest.setTransactionStatus(depositResponse.getStatus());
        transactionRequest.setSenderAccountNumber(customerWallet.getAccountNumber());
        transactionRequest.setReceiverAccountNumber(depositRequest.getReceiverAccountNumber());
        return transactionRequest;
    }

    private WalletDepositRequest createWalletDepositRequest(DepositRequest depositRequest, Wallet customerWallet) {
    WalletDepositRequest walletDepositRequest = new WalletDepositRequest();
    walletDepositRequest.setAmount(depositRequest.getAmount());
    walletDepositRequest.setId(customerWallet.getId());
    return walletDepositRequest;
    }

}

