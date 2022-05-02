package com.example.OnlineCourses.it;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerIT extends TestConfig{

    private final String REQUEST_BODY = "{\"userName\" : \"Mili\",\n"
            + "    \"password\" : \"pass\",\n"
            + "    \"active\" : true,\n"
            + "    \"roles\" : \"ROLE_USER\"\n"
            + "}";

    //@WithMockUser(username = "admin", password = "1234", roles = {"ADMIN"})
    @Test
    void insertUser() throws Exception {
        mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON).content(REQUEST_BODY)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName", equalTo("Mili")));
    }

    //@WithMockUser(username = "admin", password = "1234", roles = {"ADMIN"})
    @Test
    void getAllUsers() throws Exception {
        mockMvc.perform(get("/api/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }
}
