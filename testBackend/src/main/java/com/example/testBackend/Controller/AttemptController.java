package com.example.testBackend.Controller;

import com.example.testBackend.Entities.*;
import com.example.testBackend.Repository.AttemptRepository;
import com.example.testBackend.Service.Impl.IAttemptService;
import com.example.testBackend.Service.Impl.IQuestionService;
import com.example.testBackend.Service.Impl.ITestService;
import com.example.testBackend.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AttemptController {
    @Autowired
    private ITestService testService;

    @Autowired
    private AttemptRepository attemptRepository;
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private IAttemptService attemptService;

    @PostMapping("/launch-test")
    public ResponseEntity<?> launchTest(@RequestBody LaunchTestRequest request) {
        List<Question> selectedQuestions = questionService.getRandomQuestions(request);
        List<String> questionIds = new ArrayList<>();
        for (Question sq: selectedQuestions) {
            String qid = sq.getQuestionId();
            questionIds.add(qid);
        }

        List<AttemptedQuestion> attemptedQuestions = new ArrayList<>();
        for (String questionId : questionIds) {
            AttemptedQuestion attemptedQuestion = new AttemptedQuestion();
            attemptedQuestion.setQuestionId(questionId);
            attemptedQuestions.add(attemptedQuestion);
        }
        Attempt attempt = attemptService.createAttempt(request.getUserId(), request.getTestId(), request.getCourseId(), attemptedQuestions);
        attempt.setAttemptedQuestions(attemptedQuestions);
        LaunchTestResponse response = new LaunchTestResponse();

        response.setQuestionsList(selectedQuestions);
        response.setAttemptId(attempt.getAttemptId());
        return ResponseEntity.ok(response);
    }


    @PostMapping("/finish-test")
    public ResponseEntity<?> finishTest(@RequestBody FinishTestRequest request) {
        Attempt attempt = attemptRepository.findById(request.getAttemptId()).orElse(null);
        List<AttemptedQuestion> attemptedQuestions = request.getAttemptedQuestions();
        attempt.setAttemptedQuestions(attemptedQuestions);
        attemptService.updateAttemptAndSaveResult(attempt);
        return ResponseEntity.ok("Test attempt updated successfully");
    }

    @GetMapping("/getAttempts/{studentId}/{courseId}")
    public ResponseEntity<?> getAttemptsList(@PathVariable String studentId, @PathVariable String courseId)
    {
        ResponseEntity res = null;
        try {
            List<Attempt> attempts = attemptService.getAttempts(studentId,courseId);
            res = ResponseEntity.status(HttpStatus.OK).body(attempts);
        } catch (Exception e) {
            ExceptionResponse ex = new ExceptionResponse(e.toString());
            res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
        return res;
    }

    @GetMapping("/getAttempt/{attemptId}")
    public  ResponseEntity<?> getAttemptById(@PathVariable String attemptId)
    {
        ResponseEntity res = null;
        try {
            Attempt attempt = attemptRepository.findById(attemptId).orElse(null);
            res = ResponseEntity.status(HttpStatus.OK).body(attempt);
        } catch (Exception e) {
            ExceptionResponse ex = new ExceptionResponse(e.toString());
            res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
        return res;

    }

}
