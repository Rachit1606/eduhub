package com.example.testBackend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.PrivateKey;
import java.util.List;

@Document(collection = "tests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @Id
    private String id;
    private String userId;
    private String courseId;
    private List<String> chapterIds;
    private List<String> subchapterIds;
    private int testTime;
    private int numberOfQuestions;
    private int difficultyLevel;
    private String testName;
}