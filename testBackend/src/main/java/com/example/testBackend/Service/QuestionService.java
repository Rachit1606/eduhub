package com.example.testBackend.Service;

import com.example.testBackend.Entities.LaunchTestRequest;
import com.example.testBackend.Entities.Question;
import com.example.testBackend.Repository.QuestionRepository;
import com.example.testBackend.Service.Impl.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService implements IQuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getAllQuestionsByUserId(String userId) {
        return questionRepository.findAllByUserId(userId);
    }
    @Override
    public Optional<Question> getQuestionById(String id) {
        return questionRepository.findById(id);
    }
    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }
    @Override
    public Question updateQuestion(String id, Question updatedQuestion) {
        updatedQuestion.setQuestionId(id);
        return questionRepository.save(updatedQuestion);
    }
    @Override
    public void deleteQuestion(String id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> getRandomQuestions(LaunchTestRequest request) {
        int numberOfQuestions = request.getNumberOfQuestions();
        int easyPercentage = 50;
        int mediumPercentage = 30;
        int hardPercentage = 20;

        int difficultyLevel = request.getDifficultyLevel();

        easyPercentage -= (difficultyLevel - 1) * 10;
        mediumPercentage += (difficultyLevel - 1) * 5;
        hardPercentage += (difficultyLevel - 1) * 5;

        int easyQuestions = Math.round(numberOfQuestions * easyPercentage / 100.0f);
        int mediumQuestions = Math.round(numberOfQuestions * mediumPercentage / 100.0f);
        int hardQuestions = numberOfQuestions - easyQuestions - mediumQuestions;

        easyQuestions = Math.max(0, easyQuestions);
        mediumQuestions = Math.max(0, mediumQuestions);
        hardQuestions = Math.max(0, hardQuestions);

        List<Question> allQuestions = questionRepository.findByCourseIdAndChapterIdInAndSubchapterIdIn(
                request.getCourseId(), request.getChapterIds(), request.getSubchapterIds());

        Collections.shuffle(allQuestions);

        List<Question> selectedEasyQuestions = new ArrayList<>();
        List<Question> selectedMediumQuestions = new ArrayList<>();
        List<Question> selectedHardQuestions = new ArrayList<>();


        for (Question question : allQuestions) {
            if (easyQuestions > 0 && question.getDifficulty().equals("easy")) {
                selectedEasyQuestions.add(question);
                easyQuestions--;
            } else if (mediumQuestions > 0 && question.getDifficulty().equals("medium")) {
                selectedMediumQuestions.add(question);
                mediumQuestions--;
            } else if (hardQuestions > 0 && question.getDifficulty().equals("hard")) {
                selectedHardQuestions.add(question);
                hardQuestions--;
            }

            if (easyQuestions == 0 && mediumQuestions == 0 && hardQuestions == 0) {
                break;
            }
        }
        List<Question> selectedQuestions = new ArrayList<>();
        selectedQuestions.addAll(selectedEasyQuestions);
        selectedQuestions.addAll(selectedMediumQuestions);
        selectedQuestions.addAll(selectedHardQuestions);
        Collections.shuffle(selectedQuestions);
        return selectedQuestions;
    }
    @Override
    public List<Question> getAllQuestionsByUserAndCourse(String userId, String courseId) {
        return questionRepository.findByUserIdAndCourseId(userId, courseId);
    }
}
