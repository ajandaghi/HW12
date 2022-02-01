package org.bank.exceptions;

public class InvalidCmdFormat extends RuntimeException {
    public InvalidCmdFormat(String message) {
        super(message);
    }
}
