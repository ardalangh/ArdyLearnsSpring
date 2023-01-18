package com.ardyspringpractice.restfulwebservices.exception;

import com.ardyspringpractice.restfulwebservices.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception exp, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDate.now(), exp.getMessage(), request.getDescription(false)
        );
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception exp, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDate.now(), exp.getMessage(), request.getDescription(false)
        );
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }
}