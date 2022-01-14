package com.example.OnlineCourses.service;

import com.example.OnlineCourses.domain.model.Course;
import com.example.OnlineCourses.domain.model.Program;
import com.example.OnlineCourses.domain.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    @Override
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    @Override
    public Program findByName(String programName) {
        return programRepository.findByName(programName);
    }

//    @Override
//    public Course findCourseByName(String courseName) {
//        List<Program> programs = programRepository.findAll();
//        for (Program p: programs
//        ) {
//            for (Course c: p.getCourses()
//            ) {
//                if(c.getName().equals(courseName)){
//                    return c;
//                }
//            }
//        }
//        //Optional<Stream<Course>> first = programs.stream().map(p -> p.getCourses().stream().filter(c -> c.getName().equals(courseName))).findFirst();
//        return null;
//    }

    @Override
    public Program insertProgram(Program program) {
        return programRepository.insert(program);
    }

    @Override
    public Program insertCourse(String name, Course course) {
        return null;
    }

    @Override
    public Program updateProgram(Program program, String name) {
        return null;
    }

    @Override
    public String deleteProgramByName(String name) {
        return null;
    }
}
