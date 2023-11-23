package org.example.util.exception;

public class InvalidProductException extends ProductExcption{

    public InvalidProductException(String message, Integer status) {
        super(message, status);
    }
}
