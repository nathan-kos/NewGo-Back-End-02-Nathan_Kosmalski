package org.example.util.exception;

public class ExistingProductException extends ProductException {

    public ExistingProductException(String message, Integer status) {
        super(message, status);
    }
}
