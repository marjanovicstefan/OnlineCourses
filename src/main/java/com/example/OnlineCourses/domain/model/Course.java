package com.example.OnlineCourses.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private String name;

    private double price;

    private String time;

    private int numberOfArticles;

    private int numberOfExercises;

    private Boolean accessOnMobileAndTv;

    private Boolean certificate;

    private List<Author> authors;

    private double review;

    private String programmingLanguage;
}
