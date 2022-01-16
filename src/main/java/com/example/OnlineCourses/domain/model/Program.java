package com.example.OnlineCourses.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "Program")
public class Program {

    @Id
    private String id;

    @Field
    private String name;

    @Field
    private List<Course> courses;

    public Program() {
    }

    public Program(String id, String name, List<Course> courses) {
        this.id = id;
        this.name = name;
        this.courses = courses;
    }

    public String getName(){
        return name;
    }

    public List<Course> getCourses(){
        return courses;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCourses(List<Course> courses){
        this.courses = courses;
    }
}
