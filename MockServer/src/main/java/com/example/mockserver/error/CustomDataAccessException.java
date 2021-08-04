package com.example.mockserver.error;

public class CustomDataAccessException extends CustomException {

    public CustomDataAccessException(String message, String code) {
        super(message, code);
    }

}
