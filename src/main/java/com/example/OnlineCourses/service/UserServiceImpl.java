package com.example.OnlineCourses.service;

import com.example.OnlineCourses.domain.model.Course;
import com.example.OnlineCourses.domain.model.User;
import com.example.OnlineCourses.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProgramServiceImpl programService;

    @Override
    public User insertUser(User user) {
        return userRepository.insert(user);
    }

    @Override
    public String buyCourse(String username, String courseName) {

        User user = userRepository.findByUserName(username).orElseThrow(()-> new RuntimeException("User not found!"));
        Course courseByName = programService.findCourseByName(courseName);
        user.setCourse(courseByName);
        User save = userRepository.save(user);
        if(save != null){
            return "Course successfully bought!";
        }
        return "Course not found";
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
