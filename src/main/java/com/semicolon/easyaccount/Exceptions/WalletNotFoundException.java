package com.semicolon.easyaccount.Exceptions;

public class WalletNotFoundException extends Exception {
    public WalletNotFoundException(String message, Long id) {
        super(message);
    }
}
