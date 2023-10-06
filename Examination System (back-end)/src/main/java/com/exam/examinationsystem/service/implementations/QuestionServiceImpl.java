package com.exam.examinationsystem.service.implementations;

import com.exam.examinationsystem.models.exam.Question;
import com.exam.examinationsystem.models.exam.Quiz;
import com.exam.examinationsystem.repository.QuestionRepository;
import com.exam.examinationsystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
<<<<<<< HEAD
        try {
            return this.questionRepository.save(question);
        }catch (Exception e) {
            System.out.println("Message: " + e);
        }
=======
>>>>>>> 1522421dd65efcacf18d54279702ddb9ed3b732a
        return this.questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestions() {
        return new LinkedHashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Question getQuestion(Long questionId) {
        return this.questionRepository.findById(questionId).get();
    }

    @Override
    public Set<Question> getQuestionOfQuiz(Quiz quiz) {
        return this.questionRepository.findByQuiz(quiz);
    }

    @Override
    public void deleteQuestion(Long qid) {
<<<<<<< HEAD
        try {
            this.questionRepository.deleteById(qid);
        } catch (Exception e) {
            System.out.println("Message: " +e);
        }
=======
        this.questionRepository.deleteById(qid);
>>>>>>> 1522421dd65efcacf18d54279702ddb9ed3b732a
    }
}
