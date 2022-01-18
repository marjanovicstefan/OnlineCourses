package com.example.OnlineCourses.domain.exception;

public class FormValidationException extends RuntimeException{

    private static final long serialVersionUID = -4258633257326161578L;

    public FormValidationException(String message) {
        super(message);
    }
}
