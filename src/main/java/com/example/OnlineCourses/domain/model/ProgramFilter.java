package com.example.OnlineCourses.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ProgramFilter {

    private String path;
    private Object value;
    private String operation;

    public ProgramFilter() {
    }

    public ProgramFilter(String path, Object value, String operation) {
        this.path = path;
        this.value = value;
        this.operation = operation;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
