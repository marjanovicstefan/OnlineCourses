package com.example.OnlineCourses.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {

    private String name;

    private String job;

    private String about;
}
