package com.example.testBackend.Service.Impl;

import com.example.testBackend.Entities.Attempt;
import com.example.testBackend.Entities.AttemptedQuestion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAttemptService {
    public void updateAttemptAndSaveResult(Attempt attempt);

    public Attempt createAttempt(String userId, String testId, String courseId, List<AttemptedQuestion> attemptedQuestions);

    public List<Attempt> getAttempts(String studentId, String courseId );
}
