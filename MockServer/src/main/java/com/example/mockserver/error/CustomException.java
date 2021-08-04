package com.example.mockserver.error;

public class CustomException extends Exception {

    private String code;

    public CustomException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
