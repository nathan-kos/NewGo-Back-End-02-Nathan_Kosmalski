package org.example.util.exception;

public class InternalServerErrorException extends ProductExcption{

    public InternalServerErrorException(String message, Integer status) {
        super(message, status);
    }

}
