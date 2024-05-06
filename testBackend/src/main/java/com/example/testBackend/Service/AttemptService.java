package com.example.testBackend.Service;

import com.example.testBackend.Entities.Attempt;
import com.example.testBackend.Entities.AttemptedQuestion;
import com.example.testBackend.Entities.FinishTestRequest;
import com.example.testBackend.Entities.Question;
import com.example.testBackend.Repository.AttemptRepository;
import com.example.testBackend.Repository.QuestionRepository;
import com.example.testBackend.Service.Impl.IAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttemptService implements IAttemptService {

    @Autowired
    private AttemptRepository attemptRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Attempt createAttempt(String userId, String testId, String courseId, List<AttemptedQuestion> attemptedQuestions) {
        Attempt attempt = new Attempt();
        attempt.setStudentId(userId);
        attempt.setTestId(testId);
        attempt.setCourseId(courseId);
        attempt.setAttemptedQuestions(attemptedQuestions);
        return attemptRepository.save(attempt);
    }

    @Override
    public void updateAttemptAndSaveResult(Attempt attempt) {
        if (attempt != null) {
            int totalMarks = calculateTotalMarks(attempt);
            int obtainedMarks = calculateObtainedMarks(attempt);
            String result = calculateResult(totalMarks, obtainedMarks);
            attempt.setTotalMarks(totalMarks);
            attempt.setObtainedMarks(obtainedMarks);
            attempt.setResult(result);
            attemptRepository.save(attempt);
        }
    }
    private int calculateTotalMarks(Attempt attempt) {
        List<AttemptedQuestion> attemptedQuestions = attempt.getAttemptedQuestions();
        int totalMarks = 0;
        for (AttemptedQuestion attemptedQuestion : attemptedQuestions) {
            Question question = questionRepository.findById(attemptedQuestion.getQuestionId()).orElse(null);
            if (question != null) {
                totalMarks += question.getPositiveMarks();
            }
        }
        return totalMarks;
    }

    private int calculateObtainedMarks(Attempt attempt) {
        List<AttemptedQuestion> attemptedQuestions = attempt.getAttemptedQuestions();
        int obtainedMarks = 0;
        for (AttemptedQuestion attemptedQuestion : attemptedQuestions) {
            Question question = questionRepository.findById(attemptedQuestion.getQuestionId()).orElse(null);
            if (question != null) {
                if (isAnswerCorrect(question, attemptedQuestion)) {
                    obtainedMarks += question.getPositiveMarks();
                } else {
                    obtainedMarks -= question.getNegativeMarks();
                }
            }
        }
        return obtainedMarks;
    }

    private boolean isAnswerCorrect(Question question, AttemptedQuestion attemptedQuestion) {
        List<String> selectedOptions = attemptedQuestion.getSelectedOptions();
        List<String> correctOptions = question.getCorrectOptions();
        return selectedOptions != null && selectedOptions.equals(correctOptions);
    }

    private String calculateResult(int totalMarks, int obtainedMarks) {
        double percentage = (double) obtainedMarks / totalMarks * 100;
        if (percentage >= 40) {
            return "Pass";
        } else {
            return "Fail";
        }
    }
    @Override
    public List<Attempt> getAttempts(String studentId, String courseId) {
        return attemptRepository.findByStudentIdAndCourseId(studentId,courseId);
    }

}
