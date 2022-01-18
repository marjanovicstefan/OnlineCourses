package com.example.OnlineCourses.domain.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UnsupportedFilterOperationException extends RuntimeException implements GraphQLError {

    private static final long serialVersionUID = -584278991885155802L;

    public UnsupportedFilterOperationException(String message) {
        super(message);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return Collections.emptyList();
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.OperationNotSupported;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return Collections.singletonMap("errorCode", "OC-4002");
    }
}
