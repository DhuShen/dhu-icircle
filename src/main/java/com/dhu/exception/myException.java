package com.dhu.exception;

public class myException extends RuntimeException{
    public myException(String message, Throwable cause) {
        super(message, cause);
    }
}
