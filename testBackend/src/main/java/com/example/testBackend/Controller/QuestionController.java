package com.example.testBackend.Controller;

import com.example.testBackend.Entities.ExceptionResponse;
import com.example.testBackend.Entities.Question;
import com.example.testBackend.Service.Impl.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/qb")
public class QuestionController {
    @Autowired
    private IQuestionService questionService;

    @GetMapping("/getAllQuestions")
    public ResponseEntity getAllQuestions()
    {
        ResponseEntity  res;
        try{
            List<Question> questions =  questionService.getAllQuestions();
            res = ResponseEntity.status(HttpStatus.OK).body(questions);
        }catch ( Exception e)
        {
            ExceptionResponse ex = new ExceptionResponse(e.toString());
            res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
        return res;
    }

    @GetMapping("/getAllQuestions/{userId}")
    public ResponseEntity getAllQuestionsByUserId(@PathVariable String userId) {
        ResponseEntity  res;
        try{
            List<Question> questions = questionService.getAllQuestionsByUserId(userId);
            res = ResponseEntity.status(HttpStatus.OK).body(questions);
        }catch ( Exception e)
        {
            ExceptionResponse ex = new ExceptionResponse(e.toString());
            res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
        return res;
    }


    @GetMapping("/getQuestion/{id}")
    public ResponseEntity getQuestionById(@PathVariable String id)
    {
        ResponseEntity  res ;
        try{
            Optional<Question> question =  questionService.getQuestionById(id);
            res = ResponseEntity.status(HttpStatus.OK).body(question);
        }catch ( Exception e)
        {
            ExceptionResponse ex = new ExceptionResponse(e.toString());
            res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
        return res;
    }

    @PostMapping("/addQuestion")
    public ResponseEntity addQuestion(@RequestBody Question question)
    {
        ResponseEntity  res;
        try{
            Question q = questionService.addQuestion(question);
            res = ResponseEntity.status(HttpStatus.OK).body(q);
        }catch ( Exception e)
        {
            ExceptionResponse ex = new ExceptionResponse(e.toString());
            res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
        return res;
    }

    @PutMapping("/updateQuestion/{id}")
    public ResponseEntity updateQuestion(@PathVariable String id, @RequestBody Question updatedQuestion)
    {
        ResponseEntity  res;
        try{
            Question q = questionService.updateQuestion(id, updatedQuestion);
            res = ResponseEntity.status(HttpStatus.OK).body(q);
        }catch ( Exception e)
        {
            ExceptionResponse ex = new ExceptionResponse(e.toString());
            res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
        return res;
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public ResponseEntity deleteQuestion(@PathVariable String id) {
        ResponseEntity  res;
        try{
            questionService.deleteQuestion(id);
            res = ResponseEntity.status(HttpStatus.OK).body("Question Deleted Successfully");
        }catch ( Exception e)
        {
            ExceptionResponse ex = new ExceptionResponse(e.toString());
            res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
        return res;
    }

    @GetMapping("/getAllQuestions/{userId}/{courseId}")
    public ResponseEntity getAllQuestionsByUserAndCourse(@PathVariable String userId, @PathVariable String courseId) {
        ResponseEntity res;
        try {
            List<Question> questions = questionService.getAllQuestionsByUserAndCourse(userId, courseId);
            res = ResponseEntity.status(HttpStatus.OK).body(questions);
        } catch (Exception e) {
            ExceptionResponse ex = new ExceptionResponse(e.toString());
            res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
        return res;
    }
}

