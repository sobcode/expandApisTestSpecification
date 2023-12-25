package com.app.expandapistesttask.exception;

public class WrongCredentialsException extends Exception {

    public WrongCredentialsException(String message) {
        super(message);
    }

    public WrongCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
