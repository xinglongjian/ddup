package com.xingtan.customer.mapper;

import com.xingtan.customer.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {
    Question getQuestionById(@Param("id") long id);
    List<Question> getQuestionsByTypeId(@Param("typeId") long typeId);
    List<Question> getQuestionsByName(@Param("name") String name);
    void insertQuestion(Question question);
    void updateQuestion(Question question);
    void deleteQuestion(@Param("id") long id);
}
