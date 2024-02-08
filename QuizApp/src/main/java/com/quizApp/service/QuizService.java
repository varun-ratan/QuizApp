package com.quizApp.service;

import com.quizApp.dao.QuestionDao;
import com.quizApp.dao.QuizDao;
import com.quizApp.model.Question;
import com.quizApp.model.QuestionWrapper;
import com.quizApp.model.Quiz;
import com.quizApp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category,int numQ,String title)
    {
        List<Question> questions=questionDao.findRandomQuestionByCategory(category,numQ);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz=quizDao.findById(id);
        List<Question> questionFromDB=quiz.get().getQuestions();
        List<QuestionWrapper> questionForuser=new ArrayList<>();
        for(Question q:questionFromDB)
        {
            QuestionWrapper qw=new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionForuser.add(qw);
        }

        return new ResponseEntity<>(questionForuser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int right=0;
        int i=0;
        for(Response response:responses)
        {
            if(response.getResponse().equals(questions.get(i++).getRightAnswer()))
                right++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
