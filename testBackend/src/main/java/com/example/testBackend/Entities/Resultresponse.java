package com.example.testBackend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resultresponse {
    private int totalMarks;
    private int obtainedMarks;
    private String result;
    private List<QuestionResult> questionResults;
}

