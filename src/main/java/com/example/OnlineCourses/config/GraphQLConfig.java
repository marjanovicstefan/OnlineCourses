package com.example.OnlineCourses.config;

import com.coxautodev.graphql.tools.SchemaParser;
import com.example.OnlineCourses.domain.repository.ProgramRepository;
import com.example.OnlineCourses.error.GraphQLExceptionHandler;
import com.example.OnlineCourses.service.graphql.QueryResolver;
import graphql.GraphQL;
import graphql.execution.AsyncExecutionStrategy;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

    @Bean
    public GraphQL initializeGraphQL(GraphQLExceptionHandler exceptionHandler,
                                     ProgramRepository repository){
        GraphQLSchema graphQLSchema =  SchemaParser.newParser()
                .file("onlinecourses.graphql")
                .resolvers(new QueryResolver(repository))
                .scalars(ExtendedScalars.Object)
                .build()
                .makeExecutableSchema();

        return GraphQL.newGraphQL(graphQLSchema)
                .queryExecutionStrategy(new AsyncExecutionStrategy(exceptionHandler))
                .build();
    }
}
