package com.example.OnlineCourses.service;

import com.example.OnlineCourses.domain.model.User;
import com.example.OnlineCourses.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User insertUser(User user) {
        return userRepository.insert(user);
    }

    @Override
    public String buyCourse(String username, String courseName) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
