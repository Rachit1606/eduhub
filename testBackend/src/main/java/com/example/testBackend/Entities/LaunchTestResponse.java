package com.example.testBackend.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaunchTestResponse {

    private List<Question> questionsList;
    private String attemptId;

}
