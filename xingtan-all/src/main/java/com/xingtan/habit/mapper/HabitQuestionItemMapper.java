package com.xingtan.habit.mapper;

import com.xingtan.habit.entity.HabitQuestionItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HabitQuestionItemMapper {
    HabitQuestionItem getHabitItemById(@Param("id") long id);
    List<HabitQuestionItem> getHabitItemByQuestionId(@Param("questionId") long questionId);
    List<HabitQuestionItem> getHabitItemByIds(@Param("ids") List<Long> ids);
    void insertHabitItem(HabitQuestionItem habitQuestionItem);
    void updateHabitItem(HabitQuestionItem habitQuestionItem);
    void deleteHabitItem(@Param("id") long id);
}
