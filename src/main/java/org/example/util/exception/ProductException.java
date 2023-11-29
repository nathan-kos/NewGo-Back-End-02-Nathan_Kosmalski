package org.example.util.exception;

public abstract class ProductException extends Exception{

    private int code;

    public ProductException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
