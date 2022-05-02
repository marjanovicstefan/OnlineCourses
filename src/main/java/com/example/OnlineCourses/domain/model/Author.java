package com.example.OnlineCourses.domain.model;

public class Author {

    private String authorName;

    private String job;

    private String about;

    public Author(String authorName, String job, String about) {
        this.authorName = authorName;
        this.job = job;
        this.about = about;
    }

    public String getName() {
        return authorName;
    }

    public void setName(String name) {
        this.authorName = name;
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
