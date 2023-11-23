package org.example.util.exception;

public class DeactivatedProductException extends ProductExcption{

    public DeactivatedProductException(String message, int code) {
        super(message, code);
    }
}
