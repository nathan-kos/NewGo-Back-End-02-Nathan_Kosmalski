package org.example.util.exception;

public class DeactivatedProductException extends ProductException {

    public DeactivatedProductException(String message, int code) {
        super(message, code);
    }
}
