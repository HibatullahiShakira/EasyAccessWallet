package com.semicolon.easyaccount.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCustomerResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private String phoneNumber;
    private String walletId;
}
