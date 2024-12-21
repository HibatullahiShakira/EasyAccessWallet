package com.semicolon.easyaccount.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCustomerResponse {
    private String message;
    private Long customerId;
}
