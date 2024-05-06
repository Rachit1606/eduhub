package com.example.testBackend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    private String questionId;
    private String userId;
    private String courseId;
    private String chapterId;
    private String subchapterId;
    private String difficulty;
    private String topic;
    private String question;
    private List<String> options;
    private List<String> correctOptions;
    private int positiveMarks;
    private int negativeMarks;
    private String solutionDescription;
}

