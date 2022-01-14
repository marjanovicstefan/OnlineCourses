package com.example.OnlineCourses.domain.repository;

import com.example.OnlineCourses.domain.model.Program;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProgramRepository extends MongoRepository<Program, String> {

    Program findByName(String name);
    String deleteByName(String name);
}
