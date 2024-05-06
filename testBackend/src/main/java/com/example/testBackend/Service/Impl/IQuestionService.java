package com.example.testBackend.Service.Impl;

import com.example.testBackend.Entities.LaunchTestRequest;
import com.example.testBackend.Entities.Question;

import java.util.List;
import java.util.Optional;

public interface IQuestionService {
    List<Question> getAllQuestions();
    List<Question> getAllQuestionsByUserId(String userId);
    List<Question> getAllQuestionsByUserAndCourse(String userId, String courseId);
    Optional<Question> getQuestionById(String id);

    Question addQuestion(Question question);

    Question updateQuestion(String id, Question updatedQuestion);

    void deleteQuestion(String id);

    List<Question> getRandomQuestions(LaunchTestRequest request);

}
