package com.quizApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @GetMapping("/allQuestions")
    public String getAllQuestion()
    {
        return "Hi, These are your questions";
    }
}
