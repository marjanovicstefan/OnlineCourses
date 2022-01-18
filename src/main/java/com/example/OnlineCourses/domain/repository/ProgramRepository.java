package com.example.OnlineCourses.domain.repository;

import com.example.OnlineCourses.domain.model.Program;
import com.example.OnlineCourses.domain.repository.customrepository.ProgramsFilterRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProgramRepository extends MongoRepository<Program, String>, ProgramsFilterRepository {

    Optional<Program> findByName(String name);
    String deleteByName(String name);
}
