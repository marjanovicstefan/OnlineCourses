package com.example.OnlineCourses.controller;

import com.example.OnlineCourses.domain.model.User;
import com.example.OnlineCourses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/insert-user")
    public ResponseEntity<User> insertUser(@RequestBody User user){
        return ResponseEntity.ok(userService.insertUser(user));
    }
}
