package com.xingtan.customer.service.impl;

import com.xingtan.customer.entity.QuestionType;
import com.xingtan.customer.mapper.QuestionTypeMapper;
import com.xingtan.customer.service.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {

    @Autowired
    private QuestionTypeMapper questionTypeMapper;

    @Override
    public List<QuestionType> getAll() {
        return questionTypeMapper.getAll();
    }

    @Override
    public QuestionType getQuestionTypeById(long id) {
        return questionTypeMapper.getQuestionTypeById(id);
    }

    @Override
    public QuestionType getQuestionTypesByName(long name) {
        return questionTypeMapper.getQuestionTypesByName(name);
    }

    @Override
    public long insertQuestionType(QuestionType questionType) {
        questionTypeMapper.insertQuestionType(questionType);
        return questionType.getId();
    }

    @Override
    public void updateQuestionType(QuestionType questionType) {
        questionTypeMapper.updateQuestionType(questionType);
    }

    @Override
    public void deleteQuestionType(long id) {
        questionTypeMapper.deleteQuestionType(id);
    }
}
