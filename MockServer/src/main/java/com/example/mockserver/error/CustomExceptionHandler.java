package com.example.mockserver.error;

import com.example.mockserver.dao.ExceptionDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionDAO> handleGenericException(HttpServletRequest request, Throwable throwable) {
        ExceptionDAO exceptionDAO = new ExceptionDAO("Unexpected error.", "01.01.01");
        return ResponseEntity.unprocessableEntity().body(exceptionDAO);
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ExceptionDAO> handleCustomException(HttpServletRequest request, Throwable throwable) {
        var cException = (CustomException) throwable;
        ExceptionDAO exceptionDAO = new ExceptionDAO(cException.getMessage(), cException.getCode());
        return ResponseEntity.internalServerError().body(exceptionDAO);
    }

    @ExceptionHandler({CustomDataAccessException.class})
    public ResponseEntity<ExceptionDAO> handleDataAccessException(HttpServletRequest request, Throwable throwable) {
        var daException = (CustomDataAccessException) throwable;
        ExceptionDAO exceptionDAO = new ExceptionDAO(daException.getMessage(), daException.getCode());
        return ResponseEntity.unprocessableEntity().body(exceptionDAO);
    }
}
