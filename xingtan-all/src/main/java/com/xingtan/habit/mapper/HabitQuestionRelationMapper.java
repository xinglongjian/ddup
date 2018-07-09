package com.xingtan.habit.mapper;


import com.xingtan.habit.entity.HabitQuestionRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HabitQuestionRelationMapper {
    HabitQuestionRelation getRelationById(@Param("id") long id);

    List<HabitQuestionRelation> getRelationByHabitId(@Param("habitId") long habitId);

    List<HabitQuestionRelation> getRelationByQuestionId(@Param("questionId") long questionId);

    List<HabitQuestionRelation> getRelationsByIds(@Param("ids") List<Long> ids);

    void insertRelation(HabitQuestionRelation habitQuestionRelation);

    void updateRelation(HabitQuestionRelation habitQuestionRelation);

    void deleteRelationById(@Param("id") long id);

    void deleteRelation(@Param("habitId") long habitId, @Param("questionId") long questionId);

    int getRelationCountByHabitId(@Param("habitId") long habitId);
}
