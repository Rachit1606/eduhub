package com.example.testBackend.Repository;

import com.example.testBackend.Entities.Attempt;
import com.example.testBackend.Entities.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttemptRepository extends MongoRepository<Attempt, String> {

    List<Attempt> findByStudentIdAndCourseId(String studentId, String courseId);

}