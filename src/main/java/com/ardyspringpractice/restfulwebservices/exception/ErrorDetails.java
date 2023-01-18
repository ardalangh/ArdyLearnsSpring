package com.ardyspringpractice.restfulwebservices.exception;

import java.time.LocalDate;

public class ErrorDetails {
    private String message;
    private LocalDate timestamp;
    private  String details;

    public ErrorDetails( LocalDate timestamp,String message, String details) {
        super();
        this.message = message;
        this.timestamp = timestamp;
        this.details = details;
    }


    public String getMessage() {
        return message;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public String getDetails() {
        return details;
    }
}
