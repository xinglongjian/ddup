package com.xingtan.customer.service.impl;

import com.xingtan.customer.entity.Question;
import com.xingtan.customer.mapper.QuestionMapper;
import com.xingtan.customer.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public Question getQuestionById(long id) {
        return questionMapper.getQuestionById(id);
    }

    @Override
    public List<Question> getQuestionsByTypeId(long typeId) {
        return questionMapper.getQuestionsByTypeId(typeId);
    }

    @Override
    public List<Question> getQuestionsByName(String name) {
        return questionMapper.getQuestionsByName(name);
    }

    @Override
    public long insertQuestion(Question question) {
        questionMapper.insertQuestion(question);
        return question.getId();
    }

    @Override
    public void updateQuestion(Question question) {
        questionMapper.updateQuestion(question);
    }

    @Override
    public void deleteQuestion(long id) {
        questionMapper.deleteQuestion(id);
    }
}
