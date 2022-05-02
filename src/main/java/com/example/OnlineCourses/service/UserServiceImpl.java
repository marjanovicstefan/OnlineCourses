package com.example.OnlineCourses.service;

import com.example.OnlineCourses.domain.model.Course;
import com.example.OnlineCourses.domain.model.User;
import com.example.OnlineCourses.domain.model.dto.LoginDto;
import com.example.OnlineCourses.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProgramServiceImpl programService;
    private User user;

    @Override
    public User insertUser(LoginDto user) {
        Random rand = new Random();
        User userInsert = new User("6230d6a23e8c6565d1d15c22",user.getUserName(), user.getPassword(), true, "ROLE_USER", null);
        return userRepository.insert(userInsert);
    }

    @Override
    public String getCourses(String username) {
        return userRepository.findByUserName(username).get().getCourses();
    }

    @Override
    public String buyCourse(String username, String courseName) {

        User user = userRepository.findByUserName(username).orElseThrow(()-> new RuntimeException("User not found!"));
        Course courseByName = programService.findCourseByName(courseName);
        if(user.getCourses()==null){
            user.setCourses("");
        }
        String courses = user.getCourses();
        if(courses.contains(courseByName.getCourseName())){
            return "You already bought this course!";
        }
        String coursesSet;
        if(courses.equals("")) {
             coursesSet = courseByName.getCourseName();
        }else{
             coursesSet = courses+ ", " + courseByName.getCourseName();
        }

        user.setCourses(coursesSet);
        User save = userRepository.save(user);
        if(save != null){
            return "Course successfully bought!";
        }
        return "Course not found";
    }

    @Override
    public String leaveCourse(String username, String courseName) {

        User user = userRepository.findByUserName(username).orElseThrow(()-> new RuntimeException("User not found!"));
        Course courseByName = programService.findCourseByName(courseName);
        if(user.getCourses()==null){
            user.setCourses("");
        }
        String userCourses = user.getCourses();
        if(!userCourses.contains(courseByName.getCourseName())){
            return "You don't have this course in your list!";
        }
        String coursesSet;
        if(userCourses!=null) {
            if(userCourses.contains(courseByName.getCourseName())){
                if(userCourses.contains(", " + courseByName.getCourseName())){
                    coursesSet = userCourses.replace(", "+courseByName.getCourseName(),"");
                }else {
                    coursesSet = userCourses.replace(courseByName.getCourseName(), "");
                }
                user.setCourses(coursesSet);
                if(user.getCourses().isEmpty() || user.getCourses().isBlank() || user.getCourses().equals(",")){
                    user.setCourses("");
                }
            }
            User save = userRepository.save(user);
            if(save != null){
                return "You left the course!";
            }
        }

        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User login(LoginDto loginDto) {

        Optional<User> user = userRepository.findByUserName(loginDto.getUserName());

        return user.get();
    }
}
