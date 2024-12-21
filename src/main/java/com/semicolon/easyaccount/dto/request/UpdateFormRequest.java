package com.semicolon.easyaccount.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateFormRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private String phoneNumber;
}
