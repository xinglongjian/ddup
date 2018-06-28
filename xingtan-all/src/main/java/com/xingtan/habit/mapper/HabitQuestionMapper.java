package com.xingtan.habit.mapper;

import com.xingtan.habit.entity.HabitQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface HabitQuestionMapper {
    HabitQuestion getHabitQuestionById(@Param("id") long id);
    List<HabitQuestion> getHabitQuestionByIds(@Param("ids") List<Long> ids);
    void insertHabitQuestion(HabitQuestion habitQuestion);
    void updateHabitQuestion(HabitQuestion habitQuestion);
    void deleteHabitQuestion(@Param("id") long id);
    List<HabitQuestion> getHabitQuestionByTitle(@Param("title") String title);

}
