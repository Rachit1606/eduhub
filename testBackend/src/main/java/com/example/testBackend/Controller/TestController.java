package com.example.testBackend.Controller;

import com.example.testBackend.Entities.ExceptionResponse;
import com.example.testBackend.Entities.Test;
import com.example.testBackend.Service.Impl.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tests")
public class TestController {

    @Autowired
    private ITestService testService;

    @GetMapping("/getAll")
    public ResponseEntity getAllTests()
    {
        ResponseEntity  res = null;
        try{
            List<Test> tests = testService.getAllTests();
            res = ResponseEntity.status(HttpStatus.OK).body(tests);
        }catch ( Exception e)
        {
            ExceptionResponse ex = new ExceptionResponse(e.toString());
            res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
        return res;
    }
    @GetMapping("/getAll/{userId}/{courseId}")
    public ResponseEntity getAllByUserAndCourse(@PathVariable String userId, @PathVariable String courseId) {
        ResponseEntity res;
        try {
            List<Test> tests = testService.getAllByUserAndCourse(userId, courseId);
            res = ResponseEntity.status(HttpStatus.OK).body(tests);
        } catch (Exception e) {
            ExceptionResponse ex = new ExceptionResponse(e.toString());
            res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
        return res;
    }
    @GetMapping("/getTest/{id}")
    public ResponseEntity getTestById (@PathVariable String id)
    {
        ResponseEntity  res = null;
        try{
            Optional<Test> test =  testService.getTestById(id);
            res = ResponseEntity.status(HttpStatus.OK).body(test);
        }catch ( Exception e)
        {
            ExceptionResponse ex = new ExceptionResponse(e.toString());
            res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
        return res;
    }

    @GetMapping("/getAll/{courseId}")
    public ResponseEntity getAllTestsByCourseId(@PathVariable String courseId)
    {
        ResponseEntity  res = null;
        try{
            List<Test> tests = testService.getAllTestsByCourseId(courseId);
            res = ResponseEntity.status(HttpStatus.OK).body(tests);
        }catch ( Exception e)
        {
            ExceptionResponse ex = new ExceptionResponse(e.toString());
            res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
        return res;
    }

    @PostMapping("/createTest")
    public ResponseEntity createTest(@RequestBody Test test)
    {
        ResponseEntity  res = null;
        try{
            Test t = testService.createTest(test);
            res = ResponseEntity.status(HttpStatus.OK).body(t);
        }catch ( Exception e)
        {
            ExceptionResponse ex = new ExceptionResponse(e.toString());
            res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
        return res;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTest(@PathVariable String id, @RequestBody Test updatedTest)
    {
        ResponseEntity  res = null;
        try{
            Test t = testService.updateTest(id, updatedTest);
            res = ResponseEntity.status(HttpStatus.OK).body(t);
        }catch ( Exception e)
        {
            ExceptionResponse ex = new ExceptionResponse(e.toString());
            res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
        return res;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTest(@PathVariable String id) {
        ResponseEntity  res = null;
        try{
            testService.deleteTest(id);
            res = ResponseEntity.status(HttpStatus.OK).body("Test Deleted Successfully");
        }catch ( Exception e)
        {
            ExceptionResponse ex = new ExceptionResponse(e.toString());
            res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
        return res;
    }

}