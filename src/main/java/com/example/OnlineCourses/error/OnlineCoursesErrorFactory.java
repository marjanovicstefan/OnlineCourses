package com.example.OnlineCourses.error;

import com.example.OnlineCourses.domain.exception.ProgramNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class OnlineCoursesErrorFactory {

    private static final String PROGRAM_NOT_FOUND = "OC-4000";

    public Error createError(ProgramNotFoundException e){
        return createError(HttpStatus.BAD_REQUEST.value(), PROGRAM_NOT_FOUND, e.getMessage());
    }

    private Error createError(int httpCode, String errorCode, String message){
        return Error.builder()
                .httpCode(httpCode)
                .errorCode(errorCode)
                .message(message)
                .build();
    }
}
