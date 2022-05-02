package com.example.OnlineCourses.it;

import com.example.OnlineCourses.domain.repository.ProgramRepository;
import com.example.OnlineCourses.domain.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ComponentScan
public class TestConfig {

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public ProgramRepository programRepository;

    @Autowired
    public UserRepository userRepository;
//
//    @Autowired
//    public AuthenticationManager authenticationManager;
//
//    @Autowired
//    public OnlineCoursesUserDetailsServiceImpl onlineCoursesUserDetails;

    @BeforeAll
    void setUpRestTemplate() throws ServletException {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void clean(){
        programRepository.deleteAll();
        userRepository.deleteAll();
    }
}
