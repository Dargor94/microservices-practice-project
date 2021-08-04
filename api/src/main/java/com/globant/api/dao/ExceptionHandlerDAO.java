package com.globant.api.dao;

import org.springframework.http.HttpStatus;

public class ExceptionHandlerDAO {
    private String message;
    private String code;
    private String status;

    public ExceptionHandlerDAO(String message, String code, String status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
