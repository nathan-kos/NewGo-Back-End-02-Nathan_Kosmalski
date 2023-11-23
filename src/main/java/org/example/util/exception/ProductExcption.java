package org.example.util.exception;

public abstract class ProductExcption extends Exception{

    private int code;

    public ProductExcption(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
