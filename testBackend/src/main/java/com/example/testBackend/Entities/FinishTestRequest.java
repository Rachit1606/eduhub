package com.example.testBackend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinishTestRequest {
    private String attemptId;
    private List<AttemptedQuestion> attemptedQuestions;
}
