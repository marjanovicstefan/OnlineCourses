package com.example.OnlineCourses.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Author {

    private String name;

    private String job;

    private String about;

    public Author(String name, String job, String about) {
        this.name = name;
        this.job = job;
        this.about = about;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
