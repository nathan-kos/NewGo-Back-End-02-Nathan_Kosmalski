package org.example.util.exception;

public class InvalidParamsException extends ProductException {

    public InvalidParamsException(String message, Integer status) {
        super(message, status);
    }
}
