package com.example.OnlineCourses.domain.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UnsupportedTypeException extends RuntimeException implements GraphQLError {

    private static final long serialVersionUID = 4626755006439508974L;

    public UnsupportedTypeException(String message) {
        super(message);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return Collections.emptyList();
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.ValidationError;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return Collections.singletonMap("errorCode", "OC-4003");
    }
}
