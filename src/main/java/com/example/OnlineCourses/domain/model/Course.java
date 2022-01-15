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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
