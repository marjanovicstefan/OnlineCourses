package com.example.OnlineCourses.controller;

import com.example.OnlineCourses.domain.model.User;
import com.example.OnlineCourses.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    private UserController controller;

    @Mock
    private UserService userService;

    @Test
    void insertUserTest(){
        User expected = new User("1", "admin", "1234", true, "ROLE_ADMIN", null);

        PasswordEncoder encoder = new BCryptPasswordEncoder(10);
        expected.setPassword(encoder.encode(expected.getPassword()));

        when(userService.insertUser(Mockito.any())).thenReturn(expected);

        ResponseEntity<User> actual = controller.insertUser(
                new User("1", "admin", expected.getPassword(), true, "ROLE_ADMIN", null));

        assertEquals(expected, actual.getBody());
    }

    @Test
    void getAllUsersTest(){

        String expected = "Course successfully bought!";

        when(userService.buyCourse(Mockito.any(), Mockito.any())).thenReturn(expected);

        ResponseEntity<String> actual = controller.buyCourse("User", "courseName");

        assertEquals(expected, actual.getBody());
    }

    @Test
    void buyCourseTest(){
        User user = new User("1", "admin", "1234", true, "ROLE_ADMIN", null);
        List<User> expected = Collections.singletonList(user);

        when(userService.getAllUsers()).thenReturn(expected);

        ResponseEntity<List<User>> actual = controller.getAllUsers();

        assertEquals(expected, actual.getBody());
    }
}
