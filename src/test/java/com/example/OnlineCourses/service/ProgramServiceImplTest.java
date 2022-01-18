package com.example.OnlineCourses.service;

import com.example.OnlineCourses.domain.exception.ProgramNotFoundException;
import com.example.OnlineCourses.domain.model.Course;
import com.example.OnlineCourses.domain.model.Program;
import com.example.OnlineCourses.domain.repository.ProgramRepository;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProgramServiceImplTest {

    @Mock
    private ProgramRepository repository;

    @InjectMocks
    private ProgramServiceImpl service;

    @Mock
    private GraphQL graphql;

    @Test
    void getAllProgramsTest(){
        List<Program> expected = Collections.singletonList(
                new Program("1","name",Collections.emptyList()));

        when(repository.findAll()).thenReturn(expected);

        List<Program> actual = service.getAllPrograms();

        assertEquals(expected, actual);
    }

    @Test
    void getProgramByNameTest(){
        Program expected = new Program("1", "name", Collections.emptyList());

        when(repository.findByName("name")).thenReturn(java.util.Optional.of(expected));

        Program actual = service.findByName("name");

        assertEquals(expected, actual);
    }

    @Test
    void getProgramByNameExceptionTest(){
        Exception exception = assertThrows(ProgramNotFoundException.class,
                () -> service.findByName("NonExcistingName"));

        assertThat(exception).hasMessageContaining("Program with name NonExcistingName doesn't exist!");
    }

    @Test
    void updateProgramTest(){
        Program expected = new Program("1", "name", Collections.emptyList());

        when(repository.findByName("name")).thenReturn(java.util.Optional.of(expected));
        when(repository.save(expected)).thenReturn(expected);

        Program actual = service.updateProgram(new Program("1", "name",
                Collections.emptyList()), "name");

        assertEquals(expected, actual);
    }

    @Test
    void updateProgramExceptionTest(){
        // given
        Program expected = new Program();
        // when
        Exception exception = assertThrows(ProgramNotFoundException.class,
                () -> service.updateProgram(expected, "NonExcistingName"));
        // then
        assertThat(exception).hasMessageContaining("Program with NonExcistingName name doesn't exist!");
    }

    @Test
    void deleteProgramTest(){
        Program expected = new Program("1", "name", Collections.emptyList());

        when(repository.findByName("name")).thenReturn(java.util.Optional.of(expected));
        when(repository.deleteByName("name")).thenReturn("name");

        String actual = service.deleteProgramByName("name");

        assertEquals("Program with name name is deleted!", actual);
    }

    @Test
    void deleteProgramExceptionTest(){
        // given
        // when
        Exception exception = assertThrows(ProgramNotFoundException.class,
                () -> service.deleteProgramByName("NonExcistingName"));
        // then
        assertThat(exception).hasMessageContaining("Program with NonExcistingName name doesn't exist!");
    }

    @Test
    void findCourseByName(){
        Course expected = new Course("course", 100, "2 months", 10,
                10, true, true, Collections.emptyList(), 10, "Java");
        Program program = new Program("1", "program", Collections.singletonList(expected));
        List<Program> programs = Collections.singletonList(program);

        when(repository.findAll()).thenReturn(programs);

        Course actual = service.findCourseByName("course");

        assertEquals(expected, actual);
    }

    @Test
    void findCourseByNameReturnsNull(){
        Course course = new Course("course", 100, "2 months", 10,
                10, true, true, Collections.emptyList(), 10, "Java");
        Program program = new Program("1", "program", Collections.singletonList(course));
        List<Program> programs = Collections.singletonList(program);

        when(repository.findAll()).thenReturn(programs);

        Course actual = service.findCourseByName("course1");

        assertNull(actual);
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
                new ArrayList<>(),
                5,
                "JAVA");

        Program program = new Program("1", "name", new ArrayList<>());
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(course);
        program.setCourses(courses);

        when(repository.findByName("name")).thenReturn(java.util.Optional.of(program));
        when(repository.save(program)).thenReturn(program);

        Program actual = service.insertCourse("name",course);

        assertEquals(program.getCourses(), actual.getCourses());
    }

    @Test
    void insertCourseExceptionTest(){
        // given
        // when
        Exception exception = assertThrows(ProgramNotFoundException.class,
                () -> service.insertCourse("NonExcistingName", new Course()));
        // then
        assertThat(exception).hasMessageContaining("Program with NonExcistingName name doesn't exist!");
    }

    @Test
    void getAllProgramsGraphQLTest(){
        // Given
        Map<String, Object> expectedResponse = Collections.singletonMap("MerchantData", "Data");
        String query = "test";

        ExecutionResult executionResult = new ExecutionResult() {
            @Override
            public <T> T getData() {
                return null;
            }

            @Override
            public List<GraphQLError> getErrors() {
                return null;
            }

            @Override
            public Map<Object, Object> getExtensions() {
                return null;
            }

            @Override
            public Map<String, Object> toSpecification() {
                return Collections.singletonMap("MerchantData", "Data");
            }
        };

        Mockito.when(graphql.execute(query)).thenReturn(executionResult);

        // When
        Object actualResponse = service.graphQLGetAllPrograms(query);

        // Then
        assertEquals(expectedResponse, actualResponse);
    }
}
