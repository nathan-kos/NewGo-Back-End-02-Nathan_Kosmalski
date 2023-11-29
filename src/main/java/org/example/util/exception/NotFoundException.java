package org.example.util.exception;

public class NotFoundException extends ProductException {

        public NotFoundException(String message, int code) {
            super(message, code);
        }

}
