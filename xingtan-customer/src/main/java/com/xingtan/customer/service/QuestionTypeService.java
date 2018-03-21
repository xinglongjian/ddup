package com.xingtan.customer.service;

import com.xingtan.customer.entity.QuestionType;

import java.util.List;

public interface QuestionTypeService {
    List<QuestionType> getAll();
    QuestionType getQuestionTypeById(long id);
    QuestionType getQuestionTypesByName(long name);
    long insertQuestionType(QuestionType questionType);
    void updateQuestionType(QuestionType questionType);
    void deleteQuestionType(long id);
}
