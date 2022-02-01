package org.bank.exceptions;

public class InvalidNationalId extends RuntimeException {
    public InvalidNationalId(String message) {
        super(message);
    }
}
