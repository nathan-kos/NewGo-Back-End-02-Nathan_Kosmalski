package org.example.presentation.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO<T> {

    private T data;

    private String message;

    private int code;

    public ResponseDTO(T data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

}
