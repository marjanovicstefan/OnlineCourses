package com.example.OnlineCourses.service;

import com.example.OnlineCourses.domain.model.User;

import java.util.List;

public interface UserService {

    User insertUser(User user);
    String buyCourse(String username, String courseName);
    List<User> getAllUsers();
}
