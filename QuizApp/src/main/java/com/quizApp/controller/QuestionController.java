package com.quizApp.controller;

import com.quizApp.model.Question;
import com.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/allQuestions")
    public List<Question> getAllQuestion()
    {
        List<Question> questions=questionService.getAllQuestions();
        return questions;
    }

    @GetMapping("/category/{topic}")
    public List<Question> getQuestionByCategory(@PathVariable String category)
    {
        return questionService.getQuestionByCategory(category);
    }
    @PostMapping("/create")
    public String addQuestion(@RequestBody Question question)
    {
        return questionService.addQuestion(question);
    }
}
