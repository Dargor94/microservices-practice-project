package com.globant.api.error;

import org.springframework.http.HttpStatus;

public class ExceptionHandler extends Exception {

    private final String code;
    private HttpStatus status;

    public ExceptionHandler(String message, String code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }
}
