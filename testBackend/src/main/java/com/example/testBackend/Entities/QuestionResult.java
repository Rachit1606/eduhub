package com.example.testBackend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResult {
    private String questionId;
    private String question;
    private List<String> options;
    private List<String> correctOptions;
    private List<String> selectedOptions;
    private boolean isCorrect;
    private String solutionDescription;
    private int marksObtained;
}
