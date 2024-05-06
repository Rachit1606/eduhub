package com.example.testBackend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaunchTestRequest {
    private String userId;
    private String testId;
    private String courseId;
    private List<String> chapterIds;
    private List<String> subchapterIds;
    private int difficultyLevel;
    private int numberOfQuestions;

}
