package com.example.OnlineCourses.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "Program")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Program {

    @Id
    private String id;

    @Field
    private String name;

    @Field
    @Getter
    private List<Course> courses;
}
