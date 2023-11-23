package org.example.util.exception;

public class ExistingProductException extends ProductExcption{

    public ExistingProductException(String message, Integer status) {
        super(message, status);
    }
}
