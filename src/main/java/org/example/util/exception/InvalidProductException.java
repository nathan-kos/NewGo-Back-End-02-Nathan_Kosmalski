package org.example.util.exception;

public class InvalidProductException extends ProductException {

    public InvalidProductException(String message, Integer status) {
        super(message, status);
    }
}
