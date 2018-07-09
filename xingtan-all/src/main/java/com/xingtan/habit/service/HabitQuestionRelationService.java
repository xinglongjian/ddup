package com.xingtan.habit.service;

import com.xingtan.habit.entity.HabitQuestionRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author xinglongjian@qq.com
 * @Date 2018/6/23/023 16:31
 */
public interface HabitQuestionRelationService {
    HabitQuestionRelation getRelationById(long id);
    List<HabitQuestionRelation> getRelationByHabitId(long habitId);
    List<HabitQuestionRelation> getRelationByQuestionId(long questionId);
    List<HabitQuestionRelation> getRelationsByIds(List<Long> ids);
    long insertRelation(HabitQuestionRelation habitQuestionRelation);
    void updateRelation(HabitQuestionRelation habitQuestionRelation);
    void deleteRelationById(long id);
    void deleteRelation(long habitId, long questionId);
}
