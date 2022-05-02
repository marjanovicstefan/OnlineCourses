package com.example.OnlineCourses.service;

import com.example.OnlineCourses.domain.model.Course;
import com.example.OnlineCourses.domain.model.User;
import com.example.OnlineCourses.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProgramServiceImpl programService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getAllUsersTest(){
        User user = new User("1", "name", "password", true, "ROLE_ADMIN", null);
        List<User> expected = Collections.singletonList(user);

        when(userRepository.findAll()).thenReturn(expected);

        List<User> actual = userService.getAllUsers();

        assertEquals(expected, actual);
    }

//    @Test
//    void buyCourseTest(){
//        User user = new User("1", "name", "password", true, "ROLE_ADMIN", null);
//        Course course = new Course("course", 100, "2 months", 10,
//                10, true, true, Collections.emptyList(), 10, "Java");
//        String expected = "Course successfully bought!";
//        user.setCourse(course);
//
//        when(userRepository.findByUserName("name")).thenReturn(Optional.of(user));
//        when(programService.findCourseByName("course")).thenReturn(course);
//        when(userRepository.save(user)).thenReturn(user);
//
//        String actual = userService.buyCourse("name", "course");
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void buyCourseTestCourseNotBought(){
//        User user = new User("1", "name", "password", true, "ROLE_ADMIN", null);
//        Course course = new Course("course", 100, "2 months", 10,
//                10, true, true, Collections.emptyList(), 10, "Java");
//        String expected = "Course not found";
//
//        when(userRepository.findByUserName("name")).thenReturn(Optional.of(user));
//        when(programService.findCourseByName(Mockito.any())).thenReturn(course);
//
//        String actual = userService.buyCourse("name", "course1");
//
//        assertEquals(expected, actual);
//    }
}
