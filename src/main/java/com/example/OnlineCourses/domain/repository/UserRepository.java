package com.example.OnlineCourses.domain.repository;

import com.example.OnlineCourses.domain.model.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Lazy
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserName(String username);
}
