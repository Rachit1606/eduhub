package com.example.testBackend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "attempts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attempt {
    @Id
    private String attemptId;
    private String testId;
    private String studentId;
    private String courseId;
    private List<AttemptedQuestion> attemptedQuestions;
    private int totalMarks;
    private int obtainedMarks;
    private String result;
}

