package com.example.OnlineCourses.error;

import com.example.OnlineCourses.domain.exception.FormValidationException;
import com.example.OnlineCourses.domain.exception.ProgramNotFoundException;
import com.example.OnlineCourses.domain.exception.UnsupportedFilterOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlingControllerAdvice {

    @Autowired
    private OnlineCoursesErrorFactory errorFactory;

    @ExceptionHandler(ProgramNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error validationExceptionProgramNotFound(ProgramNotFoundException e){
        return  errorFactory.createError(e);
    }

    @ExceptionHandler(FormValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error formValidationException(FormValidationException e) {
        return errorFactory.createError(e);
    }

    @ExceptionHandler(UnsupportedFilterOperationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error unsupportedFilterOperationException(UnsupportedFilterOperationException e) {
        return errorFactory.createError(e);
    }
}
