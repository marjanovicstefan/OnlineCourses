package com.example.OnlineCourses.service;

import com.example.OnlineCourses.domain.model.User;
import com.example.OnlineCourses.domain.model.dto.LoginDto;

import java.util.List;

public interface UserService {

    User insertUser(LoginDto user);
    String getCourses(String username);
    String buyCourse(String username, String courseName);
    String leaveCourse(String username, String courseName);
    List<User> getAllUsers();
    User login(LoginDto loginDto);
}
