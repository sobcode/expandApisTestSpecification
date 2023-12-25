package com.app.expandapistesttask.exception;

public class WrongIdWhileCreateOrUpdateException extends Exception{

    public WrongIdWhileCreateOrUpdateException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public WrongIdWhileCreateOrUpdateException(String msg) {
        super(msg);
    }
}
