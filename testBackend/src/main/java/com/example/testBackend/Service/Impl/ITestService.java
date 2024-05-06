package com.example.testBackend.Service.Impl;

import com.example.testBackend.Entities.Test;

import java.util.List;
import java.util.Optional;

public interface ITestService {
    List<Test> getAllTests();

    Optional<Test> getTestById(String id);
    List<Test> getAllTestsByCourseId(String courseId);

    List<Test> getAllByUserAndCourse(String userId, String courseId);

    Test createTest(Test test);

    Test updateTest(String id, Test updatedTest);

    void deleteTest(String id);
}
