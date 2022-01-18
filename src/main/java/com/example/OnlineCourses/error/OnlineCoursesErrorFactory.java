package com.example.OnlineCourses.error;

import com.example.OnlineCourses.domain.exception.FormValidationException;
import com.example.OnlineCourses.domain.exception.ProgramNotFoundException;
import com.example.OnlineCourses.domain.exception.UnsupportedFilterOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class OnlineCoursesErrorFactory {

    private static final String PROGRAM_NOT_FOUND = "OC-4000";
    private static final String FORM_VALIDATION = "OC-4001";
    private static final String UNSUPPORTED_FILTER = "OC-4002";

    public Error createError(ProgramNotFoundException e){
        return createError(HttpStatus.BAD_REQUEST.value(), PROGRAM_NOT_FOUND, e.getMessage());
    }

    public Error createError(FormValidationException e){
        return createError(HttpStatus.BAD_REQUEST.value(), FORM_VALIDATION, e.getMessage());
    }

    public Error createError(UnsupportedFilterOperationException e){
        return createError(HttpStatus.BAD_REQUEST.value(), UNSUPPORTED_FILTER, e.getMessage());
    }

    private Error createError(int httpCode, String errorCode, String message){
        return Error.builder()
                .httpCode(httpCode)
                .errorCode(errorCode)
                .message(message)
                .build();
    }
}
