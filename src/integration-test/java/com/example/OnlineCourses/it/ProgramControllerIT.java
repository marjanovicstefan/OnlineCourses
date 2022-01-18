package com.example.OnlineCourses.it;

import com.example.OnlineCourses.domain.model.Course;
import com.example.OnlineCourses.domain.model.Program;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProgramControllerIT extends TestConfig{

    private Program program;
    private Course course;
    private final String BODY_REQUEST_COURSE = "{\n"
            + "\t\"name\" : \"Front End Web Developer\",\n"
            + "    \"price\" : 300,\n"
            + "    \"time\" : \"2 months\",\n"
            + "    \"numberOfArticles\" : 15,\n"
            + "    \"numberOfExercises\" : 30,\n"
            + "    \"accessOnMobileAndTv\" : true,\n"
            + "    \"certificate\" : false,\n"
            + "    \"authors\" : [\n"
            + "    {\n"
            + "        \"name\" : \"Daniel Silber-Baker\",\n"
            + "        \"job\" : \"Instructor\",\n"
            + "        \"about\" : \"Daniel Silber-Baker is a programmer, poet, and educational design expert. He has a master’s degree from NYU’s Interactive Telecommunications Program, and his professional career has stretched across the non-profit, corporate, and academic spheres.\"\n"
            + "    }\n"
            + "    ],\n"
            + "    \"review\" : 3.9,\n"
            + "    \"programmingLanguage\" : \"Angular\"\n"
            + "}";
    private final String BODY_REQUEST = "{\n"
            + "    \"name\" : \"Program\",\n"
            + "    \"courses\" : [\n"
            + "        {\n"
            + "            \"name\" : \"Front End Web Developer\",\n"
            + "            \"price\" : 300,\n"
            + "            \"time\" : \"2 months\",\n"
            + "            \"numberOfArticles\" : 15,\n"
            + "            \"numberOfExercises\" : 30,\n"
            + "            \"accessOnMobileAndTv\" : true,\n"
            + "            \"certificate\" : false,\n"
            + "            \"authors\" : [\n"
            + "                {\n"
            + "                    \"name\" : \"Daniel Silber-Baker\",\n"
            + "                    \"job\" : \"Instructor\",\n"
            + "                    \"about\" : \"Daniel Silber-Baker is a programmer, poet, and educational design expert. He has a master’s degree from NYU’s Interactive Telecommunications Program, and his professional career has stretched across the non-profit, corporate, and academic spheres.\"\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\" : \"Rachel Manning\",\n"
            + "                    \"job\" : \"Instructor\",\n"
            + "                    \"about\" : \"Rachel is a front end web developer at Acquia and spent 3 years as the curriculum developer for a Silicon Beach bootcamp. An advocate for continued learning, she is passionate about mentoring women and students in technology.\"\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\" : \"Alyssa Hope\",\n"
            + "                    \"job\" : \"Instructor\",\n"
            + "                    \"about\" : \"Alyssa is a full stack developer who was previously the lead instructor at a coding bootcamp. With a degree in International Communications, her passion is to express thoughts well, whether in code or writing.\"\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\" : \"Richard Kalehoff\",\n"
            + "                    \"job\" : \"Instructor\",\n"
            + "                    \"about\" : \"Richard is a Course Developer with a passion for teaching. He has a degree in computer science, and first worked for a nonprofit doing everything from front end web development, to backend programming, to database and server management.\"\n"
            + "                }\n"
            + "            ],\n"
            + "            \"review\" : 3.9,\n"
            + "            \"programmingLanguage\" : \"Angular\"\n"
            + "        }\n"
            + "       ]\n"
            + "     }";

    private String QUERY = "{\"query\": \"{"
            + "getAllPrograms{"
            + "id,"
            + "name,"
            + "courses{"
            + "name,"
            + "price,"
            + "time,"
            + "numberOfArticles,"
            + "numberOfExercises,"
            + "accessOnMobileAndTv,"
            + "certificate,"
            + "authors{"
            + "name,"
            + "job,"
            + "about"
            + "},"
            + "review,"
            + "programmingLanguage"
            + "}"
            + "}"
            + "}\"}";

    private String QUERY_INVALID_OPERATION = "{\"query\": \"{"
            + "getAllPrograms(filters:{path:\\\"name\\\" value:\\\"Program\\\", operation:\\\"invalid\\\"}){"
            + "id,"
            + "name,"
            + "courses{"
            + "name,"
            + "price,"
            + "time,"
            + "numberOfArticles,"
            + "numberOfExercises,"
            + "accessOnMobileAndTv,"
            + "certificate,"
            + "authors{"
            + "name,"
            + "job,"
            + "about"
            + "},"
            + "review,"
            + "programmingLanguage"
            + "}"
            + "}"
            + "}\"}";

    private String QUERY_INVALID_TYPE = "{\"query\": \"{"
            + "getAllPrograms(filters:{path:\\\"name\\\" value:true, operation:\\\"lt\\\"}){"
            + "id,"
            + "name,"
            + "courses{"
            + "name,"
            + "price,"
            + "time,"
            + "numberOfArticles,"
            + "numberOfExercises,"
            + "accessOnMobileAndTv,"
            + "certificate,"
            + "authors{"
            + "name,"
            + "job,"
            + "about"
            + "},"
            + "review,"
            + "programmingLanguage"
            + "}"
            + "}"
            + "}\"}";

    @BeforeEach
    void setUp() {
        program = new Program("1","Program", Arrays.asList(
                new Course())
        );
        course = new Course(
                "name",
                300,
                "2months",
                15,
                30,
                true,
                true,
                Collections.EMPTY_LIST,
                5,
                "JAVA");
        programRepository.save(program);
    }

    @WithMockUser(username = "admin", password = "1234", roles = {"ADMIN"})
    @Test
    void getAllPrograms() throws Exception {
        mockMvc.perform(get("/api/programs/getall")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @WithMockUser(username = "user", password = "1234", roles = {"USER"})
    @Test
    void getProgramByName() throws Exception {
        mockMvc.perform(get("/api/programs/Program")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("Program")));
    }

    @WithMockUser(username = "user", password = "1234", roles = {"USER"})
    @Test
    void getProgramByNameException() throws Exception {

        mockMvc.perform(get("/api/programs/NonExcistingName")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.httpCode", equalTo(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode", equalTo("OC-4000")))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.message", equalTo("Program with name NonExcistingName doesn't exist!")));
    }

    @WithMockUser(username = "admin", password = "1234", roles = {"ADMIN"})
    @Test
    void insertProgram() throws Exception {
        mockMvc.perform(post("/api/programs/insert")
                .contentType(MediaType.APPLICATION_JSON).content(BODY_REQUEST)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @WithMockUser(username = "admin", password = "1234", roles = {"ADMIN"})
    @Test
    void updateProgram() throws Exception {
        mockMvc.perform(put("/api/programs/update/Program")
                .contentType(MediaType.APPLICATION_JSON).content(BODY_REQUEST)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("Program")));
    }

    @WithMockUser(username = "admin", password = "1234", roles = {"ADMIN"})
    @Test
    void updateProgramException() throws Exception {
        mockMvc.perform(put("/api/programs/update/NonExcistingName")
                .contentType(MediaType.APPLICATION_JSON).content(BODY_REQUEST)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.httpCode", equalTo(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode", equalTo("OC-4000")))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.message", equalTo("Program with NonExcistingName name doesn't exist!")));
    }

    @WithMockUser(username = "admin", password = "1234", roles = {"ADMIN"})
    @Test
    void insertCourse() throws Exception {
        mockMvc.perform(post("/api/programs/course/Program")
                .contentType(MediaType.APPLICATION_JSON).content(BODY_REQUEST_COURSE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("Program")));
    }

    @WithMockUser(username = "admin", password = "1234", roles = {"ADMIN"})
    @Test
    void insertCourseException() throws Exception {
        mockMvc.perform(post("/api/programs/course/NonExcistingProgram")
                .contentType(MediaType.APPLICATION_JSON).content(BODY_REQUEST_COURSE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.httpCode", equalTo(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode", equalTo("OC-4000")))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.message", equalTo("Program with NonExcistingProgram name doesn't exist!")));
    }

    @WithMockUser(username = "admin", password = "1234", roles = {"ADMIN"})
    @Test
    void deleteProgram() throws Exception {
        mockMvc.perform(delete("/api/programs/delete/Program")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(result -> result.getResponse().equals("Program with name Program is deleted!"));
    }

    @WithMockUser(username = "admin", password = "1234", roles = {"ADMIN"})
    @Test
    void deleteProgramException() throws Exception {
        mockMvc.perform(delete("/api/programs/delete/NonExcistingProgram")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.httpCode", equalTo(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode", equalTo("OC-4000")))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.message", equalTo("Program with NonExcistingProgram name doesn't exist!")));
    }

    @WithMockUser(username = "user", password = "1234", roles = {"USER"})
    @Test
    void getAllProgramsGraphQL() throws Exception {
        mockMvc
                .perform(
                        post("/api/programs/graphql" ).contentType(MediaType.APPLICATION_JSON)
                                .content(QUERY).accept(MediaType.APPLICATION_JSON)).andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.getAllPrograms[0].name",
                        equalTo("Program")));
    }

    @WithMockUser(username = "user", password = "1234", roles = {"USER"})
    @Test
    void getAllProgramsWhenOperationInvalid() throws Exception {

        mockMvc
                .perform(
                        post("/api/programs/graphql").contentType(MediaType.APPLICATION_JSON)
                                .content(QUERY_INVALID_OPERATION).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.getAllPrograms", equalTo(null)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message", equalTo(
                        "Exception while fetching data (/getAllPrograms) : Unsupported filter operation")))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.errors[0].extensions.errorCode", equalTo("OC-4002")))
                .andReturn().getResponse().getContentAsString();
    }

    @WithMockUser(username = "user", password = "1234", roles = {"USER"})
    @Test
    void getAllProgramsWhenTypeInvalid() throws Exception {
        mockMvc
                .perform(
                        post("/api/programs/graphql").contentType(MediaType.APPLICATION_JSON)
                                .content(QUERY_INVALID_TYPE).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.getAllPrograms", equalTo(null)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message",
                        equalTo("Exception while fetching data (/getAllPrograms) : Type not supported!")))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.errors[0].extensions.errorCode", equalTo("OC-4003")))
                .andReturn().getResponse().getContentAsString();
    }
}