package com.example.OnlineCourses.service;

import com.example.OnlineCourses.domain.exception.ProgramNotFoundException;
import com.example.OnlineCourses.domain.model.Course;
import com.example.OnlineCourses.domain.model.Program;
import com.example.OnlineCourses.domain.repository.ProgramRepository;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private GraphQL graphQL;

    @Override
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    @Override
    public Program findByName(String programName) {
        return programRepository.findByName(programName).orElseThrow(
            () -> new ProgramNotFoundException(String.format("Program with name %s doesn't exist!", programName)));
    }

    @Override
    public Course findCourseByName(String courseName) {
        List<Program> programs = programRepository.findAll();
        for (Program p: programs
        ) {
            for (Course c: p.getCourses()
            ) {
                if(c.getName().equals(courseName)){
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public Program insertProgram(Program program) {
        return programRepository.insert(program);
    }

    @Override
    public Program insertCourse(String name, Course course) {
        Optional<Program> program = ofNullable(programRepository.findByName(name).orElseThrow(
                () -> new ProgramNotFoundException(String.format("Program with %s name doesn't exist!", name))));

        List<Course> courses = program.get().getCourses();
        courses.add(course);
        program.get().setCourses(courses);

        return programRepository.save(program.get());
    }

    @Override
    public Program updateProgram(Program program, String name) {
        Optional<Program> p = ofNullable(programRepository.findByName(name).orElseThrow(
                () -> new ProgramNotFoundException(String.format("Program with %s name doesn't exist!", name))));
        p.get().setName(program.getName());
        List<Course> courses = p.get().getCourses();
        for (Course c:program.getCourses()
        ) {
            courses.add(c);
        }
        p.get().setCourses(courses);

        return programRepository.save(p.get());
    }

    @Override
    public String deleteProgramByName(String name) {
        Optional<Program> p = ofNullable(programRepository.findByName(name).orElseThrow(
                () -> new ProgramNotFoundException(String.format("Program with %s name doesn't exist!", name))));
        programRepository.deleteByName(name);

        return "Program with name "+name+" is deleted!";
    }

    @Override
    public Object graphQLGetAllPrograms(String query) {
        return graphQL.execute(query).toSpecification();
    }
}
