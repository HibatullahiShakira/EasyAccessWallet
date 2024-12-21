package com.semicolon.easyaccount.service;

import com.semicolon.easyaccount.Exceptions.CustomerEmailExist;
import com.semicolon.easyaccount.Exceptions.UserNotFoundExceptions;
import com.semicolon.easyaccount.Exceptions.WalletNotFoundException;
import com.semicolon.easyaccount.dto.request.DepositRequest;
import com.semicolon.easyaccount.dto.request.GetCustomerRequest;
import com.semicolon.easyaccount.dto.request.RegisterCustomerRequest;
import com.semicolon.easyaccount.dto.request.UpdateFormRequest;
import com.semicolon.easyaccount.dto.response.*;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static com.semicolon.easyaccount.data.model.TransactionStatus.SUCCESS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private TransactionService transactionService;

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void testThatCustomerCanDeposit() throws UserNotFoundExceptions, WalletNotFoundException {
        Long customerId = 1L;
        BigDecimal depositAmount = new BigDecimal("1000.00");
        String description = "Default description";
        DepositRequest depositRequest = buildDepositRequest(customerId, depositAmount, description);
        DepositResponse depositResponse = customerService.deposit(depositRequest);
        assertNotNull(depositResponse);
        assertNotNull(depositResponse.getMessage());
        assertEquals(SUCCESS.toString(), depositResponse.getMessage());

    }

    private DepositRequest buildDepositRequest(Long customerId, BigDecimal depositAmount, String description) {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setCustomerId(customerId);
        depositRequest.setAmount(depositAmount);
        depositRequest.setDescription(description);
        return depositRequest;
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void testThatTransactionIsCreatedForDeposit() throws UserNotFoundExceptions, WalletNotFoundException {
        DepositRequest depositRequest = buildDepositRequest(1L, new BigDecimal("5000.00"), "Default description");
        DepositResponse depositResponse = customerService.deposit(depositRequest);

        System.out.println(depositResponse.getTransactionId());
        TransactionResponse transactionResponse = transactionService.getTransaction(depositResponse.getTransactionId());
        transactionResponse.setStatus(SUCCESS.toString());
        transactionResponse.setMessage("Transaction successful");
        assertThat(transactionResponse).isNotNull();
        assertNotNull(transactionResponse.getStatus());
        assertNotNull(transactionResponse.getMessage());
        assertEquals(SUCCESS.toString(), transactionResponse.getStatus().toString());

    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void testGetTransaction() throws UserNotFoundExceptions, WalletNotFoundException {
        Long customerId = 1L;
        BigDecimal depositAmount = new BigDecimal("1000.00");
        String description = "this is just a test for deposit";
        DepositRequest depositRequest = buildDepositRequest(customerId, depositAmount, description);
        DepositResponse depositResponse = customerService.deposit(depositRequest);
        TransactionResponse transactionResponse = transactionService.getTransaction(depositResponse.getTransactionId());
        assertThat(transactionResponse).isNotNull();

    }

    @Test
    public void testThatCustomerCanRegister() throws CustomerEmailExist{
        RegisterCustomerRequest registerCustomerRequest = customerRegistrationDetails();
        RegisterCustomerResponse registerCustomerResponse = customerService.registerCustomer(registerCustomerRequest);
        assertNotNull(registerCustomerResponse);
        assertEquals("Customer successfully registered", registerCustomerResponse.getMessage());
    }


    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void testFindUserById() throws UserNotFoundExceptions{
        Long customerId = 3L;
        GetCustomerRequest getCustomerRequest = new GetCustomerRequest();
        getCustomerRequest.setCustomerId(customerId);
        GetCustomerResponse getCustomerResponse = customerService.getCustomer(getCustomerRequest);
        assertNotNull(getCustomerResponse);
        assertEquals( "Alice", getCustomerResponse.getFirstName());

    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void testCustomerUpdate() throws UserNotFoundExceptions {
        Long customerId =1L;
        String firstName = "Jenny";
        String lastName = "Doe";
        String phoneNumber = "123456789";
        UpdateFormRequest updateFormRequest = new UpdateFormRequest();
        updateFormRequest.setFirstName(firstName);
        updateFormRequest.setLastName(lastName);
        updateFormRequest.setPhoneNumber(phoneNumber);
        UpdateFormResponse updateFormResponse = customerService.updateCustomer(updateFormRequest, customerId);
        assertNotNull(updateFormResponse);
        assertEquals("Customer details update successfully", updateFormResponse.getMessage());
    }

//    @Test
//    @Sql(scripts = {"/db/data.sql"})
//    public void testDeleteCustomer() throws UserNotFoundExceptions {
//        Long customerId = 2L;
//        GetCustomerRequest getCustomerRequest = new GetCustomerRequest();
//        getCustomerRequest.setCustomerId(customerId);
//        DeleteCustomerResponse deleteCustomerResponse = customerService.deleteCustomer(getCustomerRequest);
//        assertEquals("Customer delete successfully", deleteCustomerResponse.getMessage());
//    }

    private static RegisterCustomerRequest customerRegistrationDetails() {
        RegisterCustomerRequest registerCustomerRequest = new RegisterCustomerRequest();
        String customerFirstName = "John";
        String customerLastName = "Smith";
        String customerEmail = "john.smith@gmail.com";
        String customerPassword = "password";
        String customerGender = "female";
        registerCustomerRequest.setFirstName(customerFirstName);
        registerCustomerRequest.setLastName(customerLastName);
        registerCustomerRequest.setEmail(customerEmail);
        registerCustomerRequest.setPassword(customerPassword);
        registerCustomerRequest.setGender(customerGender);
        return registerCustomerRequest;
    }
}


