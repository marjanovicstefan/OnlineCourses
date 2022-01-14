package com.example.OnlineCourses.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@Document(collection = "User")
public class User {

    @Id
    private String id;

    @Field
    private String userName;

    @Field
    private String password;

    @Field
    private Boolean active;

    @Field
    private String roles;

//    @Field
//    private Course course;
}
