package com.xingtan.customer.service;

import com.xingtan.customer.entity.Question;

import java.util.List;

public interface QuestionService {
    Question getQuestionById(long id);
    List<Question> getQuestionsByTypeId(long typeId);
    List<Question> getQuestionsByName(String name);
    long insertQuestion(Question question);
    void updateQuestion(Question question);
    void deleteQuestion(long id);
}
