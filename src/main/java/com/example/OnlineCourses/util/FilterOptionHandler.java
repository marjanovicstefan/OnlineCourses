package com.example.OnlineCourses.util;

import com.example.OnlineCourses.domain.exception.UnsupportedTypeException;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@Component
public class FilterOptionHandler {

    public final Map<String, BiConsumer<Object, Criteria>> operationHandler = new HashMap<>();

    public FilterOptionHandler(){
        init();
    }

    private void init() {
        operationHandler.put("eq", this::eqHandler);
        operationHandler.put("ne", this::neHandler);
        operationHandler.put("lt", this::ltHandler);
        operationHandler.put("le", this::leHandler);
        operationHandler.put("in", this::inHandler);
        operationHandler.put("ge", this::geHandler);
        operationHandler.put("gt", this::gtHandler);
        operationHandler.put("regex", this::regexHandler);
    }

    private void eqHandler(Object value, Criteria criteria){
        criteria.is(convertValueEquals(value));
    }

    private void neHandler(Object value, Criteria criteria){
        criteria.ne(convertValueEquals(value));
    }

    private void ltHandler(Object value, Criteria criteria){
        criteria.lt(convertValue(value));
    }

    private void leHandler(Object value, Criteria criteria){
        criteria.lte(convertValue(value));
    }

    private void inHandler(Object value, Criteria criteria){
        if(value instanceof Collection){
            criteria.in(((Collection<?>) value).stream()
                    .map(this::convertValue).toArray());
            return;
        }
        throw new UnsupportedTypeException("Type not supported!");
    }

    private Object convertValue(Object value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        if (value instanceof String) {
            try {
                return (OffsetDateTime.parse((String) value));
            } catch (DateTimeParseException ex) {
                return value;
            }
        }
        throw new UnsupportedTypeException("Type not supported!");
    }

    private void geHandler(Object value, Criteria criteria) {
        criteria.gte(convertValue(value));
    }

    private void gtHandler(Object value, Criteria criteria) {
        criteria.gt(convertValue(value));
    }

    private void regexHandler(Object value, Criteria criteria) {
        if (value instanceof String) {
            criteria.regex((String) value);
            return;
        }
        throw new UnsupportedTypeException("Type not supported!");
    }

    private Object convertValueEquals(Object value){
        if(value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        if (value instanceof String) {
            return value;
        }
        if(value instanceof Boolean){
            return value;
        }
        throw new UnsupportedTypeException("Type not suported!");
    }
}
