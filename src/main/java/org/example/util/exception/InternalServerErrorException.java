package org.example.util.exception;

public class InternalServerErrorException extends ProductException {

    public InternalServerErrorException(String message, Integer status) {
        super(message, status);
    }

}
