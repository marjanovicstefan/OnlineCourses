package com.example.OnlineCourses.domain.model;

import java.util.List;

public class Course {

    private String courseName;

    private double price;

    private String time;

    private int numberOfArticles;

    private int numberOfExercises;

    private Boolean accessOnMobileAndTv;

    private Boolean certificate;

    private List<Author> authors;

    private double review;

    private String programmingLanguage;

    public Course() {
    }

    public Course(String courseName, double price, String time, int numberOfArticles, int numberOfExercises,
                  boolean accessOnMobileAndTv, boolean certificate, List<Author> authors, double review,
                  String programmingLanguage) {
        this.courseName = courseName;
        this.price = price;
        this.time = time;
        this.numberOfArticles = numberOfArticles;
        this.numberOfExercises = numberOfExercises;
        this.accessOnMobileAndTv = accessOnMobileAndTv;
        this.certificate = certificate;
        this.authors = authors;
        this.review = review;
        this.programmingLanguage = programmingLanguage;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNumberOfArticles() {
        return numberOfArticles;
    }

    public void setNumberOfArticles(int numberOfArticles) {
        this.numberOfArticles = numberOfArticles;
    }

    public int getNumberOfExercises() {
        return numberOfExercises;
    }

    public void setNumberOfExercises(int numberOfExercises) {
        this.numberOfExercises = numberOfExercises;
    }

    public Boolean getAccessOnMobileAndTv() {
        return accessOnMobileAndTv;
    }

    public void setAccessOnMobileAndTv(Boolean accessOnMobileAndTv) {
        this.accessOnMobileAndTv = accessOnMobileAndTv;
    }

    public Boolean getCertificate() {
        return certificate;
    }

    public void setCertificate(Boolean certificate) {
        this.certificate = certificate;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public double getReview() {
        return review;
    }

    public void setReview(double review) {
        this.review = review;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
}
