package com.example.OnlineCourses.service.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.OnlineCourses.domain.model.Program;
import com.example.OnlineCourses.domain.model.ProgramFilter;
import com.example.OnlineCourses.domain.repository.ProgramRepository;
import graphql.schema.DataFetchingEnvironment;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class QueryResolver implements GraphQLQueryResolver {

    private ProgramRepository repository;

    private static final String PATH_START_POINT = "courses.";

    public QueryResolver(ProgramRepository repository) {
        this.repository = repository;
    }

    public List<Program> getAllPrograms(List<ProgramFilter> filters , DataFetchingEnvironment dataFetchingEnvironment){

        List<ProgramFilter> filterList;

        if (filters == null || filters.isEmpty()) {
            filterList = Collections.emptyList();
        } else {
            filterList = filters.stream()
                    .map(filter -> new ProgramFilter(PATH_START_POINT + filter.getPath(),
                            filter.getValue(), filter.getOperation()))
                    .collect(Collectors.toList());
        }

        return repository.getAllPrograms(filterList);
    }
}
