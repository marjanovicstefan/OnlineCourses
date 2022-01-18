package com.example.OnlineCourses.domain.repository.customrepository;

import com.example.OnlineCourses.domain.model.Program;
import com.example.OnlineCourses.domain.model.ProgramFilter;

import java.util.List;

public interface ProgramsFilterRepository {

    List<Program> getAllPrograms(List<ProgramFilter> filters);
}
