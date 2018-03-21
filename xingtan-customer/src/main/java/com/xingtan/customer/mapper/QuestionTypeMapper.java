package com.xingtan.customer.mapper;

import com.xingtan.customer.entity.QuestionType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionTypeMapper {
    List<QuestionType> getAll();
    QuestionType getQuestionTypeById(@Param("id") long id);
    QuestionType getQuestionTypesByName(@Param("name") long name);
    void insertQuestionType(QuestionType questionType);
    void updateQuestionType(QuestionType questionType);
    void deleteQuestionType(@Param("id") long id);
}
