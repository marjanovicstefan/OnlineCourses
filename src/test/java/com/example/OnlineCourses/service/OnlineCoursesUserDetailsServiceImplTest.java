package com.example.OnlineCourses.service;

import com.example.OnlineCourses.domain.model.User;
import com.example.OnlineCourses.domain.repository.UserRepository;
import com.example.OnlineCourses.security.OnlineCoursesUserDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OnlineCoursesUserDetailsServiceImplTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    OnlineCoursesUserDetailsServiceImpl service;

    @Test
    void testLoadUserByUsername() {
        //Given
        User user = new User("1", "username", "password", true, "ROLE_USER", null);
        UserDetails expectedResult =  new OnlineCoursesUserDetails(user);

        when(repository.findByUserName("username")).thenReturn(Optional.of(user));

        //When
        UserDetails actualResult = service.loadUserByUsername("username");

        //Then
        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult);
    }


    @Test
    void testLoadUserByUsernameThrowException() {
        //Given
        //When
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("username"));

        //Then
        assertThat(exception).hasMessageContaining("User with username: "+"username"+ " not found");
    }
}
