package com.example.OnlineCourses.controller;

import com.example.OnlineCourses.domain.model.User;
import com.example.OnlineCourses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/insert-user")
    public ResponseEntity<User> insertUser(@RequestBody User user){
        return ResponseEntity.ok(userService.insertUser(user));
    }

    @PostMapping("/course/{courseName}")
    public ResponseEntity<String> buyCourse(@Value("#{request.userPrincipal.principal.username}") String username,
                                            @PathVariable String courseName){
        return ResponseEntity.ok(userService.buyCourse(username, courseName));
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
