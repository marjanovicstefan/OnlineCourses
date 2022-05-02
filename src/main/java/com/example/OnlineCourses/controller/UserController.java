package com.example.OnlineCourses.controller;

import com.example.OnlineCourses.domain.model.User;
import com.example.OnlineCourses.domain.model.dto.LoginDto;
import com.example.OnlineCourses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
@Lazy
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> insertUser(@RequestBody LoginDto user){
        //PasswordEncoder encoder = new BCryptPasswordEncoder(10);
        //user.setPassword(encoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.insertUser(user));
    }

    @PostMapping("/{username}/course/{courseName}")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> buyCourse(@PathVariable String username,
                                            @PathVariable String courseName){
        return ResponseEntity.ok(userService.buyCourse(username, courseName));
    }

    @PostMapping("/{username}/leaveCourse/{courseName}")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> leaveCourse(@PathVariable String username,
                                            @PathVariable String courseName){
        return ResponseEntity.ok(userService.leaveCourse(username, courseName));
    }

    @GetMapping("/{username}")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> leaveCourse(@PathVariable String username){
        return ResponseEntity.ok(userService.getCourses(username));
    }

    @GetMapping()
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/login")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(userService.login(loginDto));
    }
}
