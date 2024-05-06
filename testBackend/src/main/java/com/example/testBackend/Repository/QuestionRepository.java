package com.example.testBackend.Repository;

import com.example.testBackend.Entities.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuestionRepository extends MongoRepository<Question, String> {
    List<Question> findAllByUserId(String userId);

    List<Question> findByUserIdAndCourseId(String userId, String courseId);

    List<Question> findByCourseIdAndChapterIdInAndSubchapterIdIn(String courseId, List<String> chapterIds, List<String> subchapterIds);
}
