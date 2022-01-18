package com.example.OnlineCourses.domain.repository.customrepository;

import com.example.OnlineCourses.domain.exception.UnsupportedFilterOperationException;
import com.example.OnlineCourses.domain.model.Program;
import com.example.OnlineCourses.domain.model.ProgramFilter;
import com.example.OnlineCourses.util.FilterOptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgramsFilterRepositoryImpl implements ProgramsFilterRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private FilterOptionHandler optionHandler;

    @Override
    public List<Program> getAllPrograms(List<ProgramFilter> filters) {

        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();

        if(filters != null && !filters.isEmpty()){
            for(ProgramFilter filter : filters) {
                Criteria criteria = Criteria.where(filter.getPath());
                Optional.ofNullable(optionHandler.operationHandler.get(filter.getOperation()))
                        .orElseThrow(() -> new UnsupportedFilterOperationException("Unsupported filter operation"))
                        .accept(filter.getValue(), criteria);

                criteriaList.add(criteria);
            }
            query.addCriteria(
                    new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()])));
        }
        return mongoTemplate.find(query, Program.class);
    }
}