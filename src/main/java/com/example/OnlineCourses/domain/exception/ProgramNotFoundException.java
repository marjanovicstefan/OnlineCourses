package com.example.OnlineCourses.domain.exception;

public class ProgramNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6038146028901770109L;

    public ProgramNotFoundException(String message) {
        super(message);
    }
}
