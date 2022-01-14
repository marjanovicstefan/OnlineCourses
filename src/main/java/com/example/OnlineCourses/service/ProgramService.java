package com.example.OnlineCourses.service;

import com.example.OnlineCourses.domain.model.Course;
import com.example.OnlineCourses.domain.model.Program;

import java.util.List;

public interface ProgramService {

    public List<Program> getAllPrograms();
    public Program findByName(String programName);
    //public Course findCourseByName(String courseName);
    //public Object graphQLGetAllPrograms(String query);
    public Program insertProgram(Program program);
    public Program insertCourse(String name, Course course);
    public Program updateProgram(Program program, String name);
    public String deleteProgramByName(String name);
}
