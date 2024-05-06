package com.example.testBackend.Service;

import com.example.testBackend.Entities.Question;
import com.example.testBackend.Entities.Test;
import com.example.testBackend.Repository.TestRepository;
import com.example.testBackend.Service.Impl.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService implements ITestService {
    @Autowired
    private TestRepository testRepository;

    @Override
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }
    @Override
    public List<Test> getAllTestsByCourseId(String courseId) {
        return testRepository.findByCourseId(courseId);
    }

    @Override
    public Optional<Test> getTestById(String id) {
        return testRepository.findById(id);
    }

    @Override
    public Test createTest(Test test) {
        return testRepository.save(test);
    }

    @Override
    public Test updateTest(String id, Test updatedTest) {
        updatedTest.setId(id);
        return testRepository.save(updatedTest);
    }

    @Override
    public void deleteTest(String id) {
        testRepository.deleteById(id);
    }

    @Override
    public List<Test> getAllByUserAndCourse(String userId, String courseId) {
        return testRepository.findByUserIdAndCourseId(userId, courseId);
    }
}
