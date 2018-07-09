package com.xingtan.habit.service.impl;

import com.xingtan.habit.entity.HabitQuestionRelation;
import com.xingtan.habit.mapper.HabitQuestionRelationMapper;
import com.xingtan.habit.service.HabitQuestionRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xinglongjian@qq.com
 * @Date 2018/6/23/023 16:34
 */
@Service
public class HabitQuestionRelationServiceImpl implements HabitQuestionRelationService {

    @Autowired
    private HabitQuestionRelationMapper habitQuestionRelationMapper;

    @Override
    public HabitQuestionRelation getRelationById(long id) {
        return habitQuestionRelationMapper.getRelationById(id);
    }

    @Override
    public List<HabitQuestionRelation> getRelationByHabitId(long habitId) {
        return habitQuestionRelationMapper.getRelationByHabitId(habitId);
    }

    @Override
    public List<HabitQuestionRelation> getRelationByQuestionId(long questionId) {
        return habitQuestionRelationMapper.getRelationByQuestionId(questionId);
    }

    @Override
    public List<HabitQuestionRelation> getRelationsByIds(List<Long> ids) {
        return habitQuestionRelationMapper.getRelationsByIds(ids);
    }

    @Override
    public long insertRelation(HabitQuestionRelation habitQuestionRelation) {
        habitQuestionRelationMapper.insertRelation(habitQuestionRelation);
        return habitQuestionRelation.getId();
    }

    @Override
    public void updateRelation(HabitQuestionRelation habitQuestionRelation) {
        habitQuestionRelationMapper.updateRelation(habitQuestionRelation);
    }

    @Override
    public void deleteRelationById(long id) {
        habitQuestionRelationMapper.deleteRelationById(id);
    }

    @Override
    public void deleteRelation(long habitId, long questionId) {
        habitQuestionRelationMapper.deleteRelation(habitId, questionId);
    }
}
