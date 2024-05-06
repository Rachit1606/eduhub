package com.example.testBackend.Repository;

import com.example.testBackend.Entities.Question;
import com.example.testBackend.Entities.Test;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends MongoRepository<Test, String> {
    List<Test> findAllByUserId(String userId);

    List<Test> findByUserIdAndCourseId(String userId, String courseId);

    List<Test> findByCourseId(String courseId);
}
