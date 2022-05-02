package com.example.OnlineCourses.controller;

import com.example.OnlineCourses.domain.model.Author;
import com.example.OnlineCourses.domain.model.Course;
import com.example.OnlineCourses.domain.model.Program;
import com.example.OnlineCourses.service.ProgramService;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProgramControllerTest {

    @InjectMocks
    private ProgramController controller;

    @Mock
    private ProgramService service;

    @Test
    void getAllProgramsTest(){
        List<Program> expected = Collections.singletonList(
                new Program("1","name",Collections.emptyList()));

        when(service.getAllPrograms()).thenReturn(expected);

        ResponseEntity<List<Program>> actual = controller.getAllPrograms();

        assertEquals(expected, actual.getBody());
    }

    @Test
    void getProgramByNameTest(){
        Program expected = new Program("1", "name", Collections.emptyList());

        when(service.findByName("name")).thenReturn(expected);

        ResponseEntity<Program> actual = controller.getProgramByName("name");

        assertEquals(expected, actual.getBody());
    }

    @Test
    void insertProgramTest(){

        Program expected = new Program("1", "name", Collections.emptyList());

        lenient().when(service.insertProgram(expected)).thenReturn(expected);

        ResponseEntity<Program> actual = controller.insertProgram(new Program("1", "name",
                Collections.emptyList()));

        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    void insertCourseTest(){
        Course course = new Course(
                "name",
                150,
                "1month",
                5,
                5,
                true,
                true,
                Collections.emptyList(),
                5,
                "JAVA");

        when(service.insertCourse("name",course)).thenReturn(new Program(
                "1",
                "name",
                Collections.singletonList(course)));

        ResponseEntity<Program> actual = controller.insertCourse("name",course);

        assertTrue(actual.getBody().getCourses().contains(course));
    }

    @Test
    void updateProgramTest(){
        Program expected = new Program("1", "name", Collections.emptyList());

        lenient().when(service.updateProgram(expected, "name1")).thenReturn(expected);

        ResponseEntity<Program> actual = controller.updateProgram(new Program("1", "name",
                Collections.emptyList()), "name1");

        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    void deleteProgramTest(){
        when(service.deleteProgramByName("name")).thenReturn("");

        ResponseEntity<String> actual = controller.deleteProgram("name");

        assertEquals("", actual.getBody());
    }

    @Test
    void testGetAllProgramsGraphQLTest() {
        // Given
        Map<String, Object> expectedResponse = Collections.singletonMap("Program data", "Data");
        Map<String, Object> query = Collections.singletonMap("query", "query");

        Mockito.when(service.graphQLGetAllPrograms((String) query.get("query")))
                .thenReturn(expectedResponse);

        // When
        Object actualResponse = controller.getAllGraphQLPrograms(query).getBody();

        // Then
        assertEquals(expectedResponse, actualResponse);
    }
}
