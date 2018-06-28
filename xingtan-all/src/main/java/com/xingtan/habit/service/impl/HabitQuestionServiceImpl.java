package com.xingtan.habit.service.impl;

import com.xingtan.habit.entity.HabitQuestion;
import com.xingtan.habit.mapper.HabitQuestionMapper;
import com.xingtan.habit.service.HabitQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xinglongjian@qq.com
 * @Date 2018/6/23/023 16:40
 */
@Service
public class HabitQuestionServiceImpl implements HabitQuestionService {

    @Autowired
    private HabitQuestionMapper habitQuestionMapper;

    @Override
    public HabitQuestion getHabitQuestionById(long id) {
        return habitQuestionMapper.getHabitQuestionById(id);
    }

    @Override
    public List<HabitQuestion> getHabitQuestionByIds(List<Long> ids) {
        return habitQuestionMapper.getHabitQuestionByIds(ids);
    }

    @Override
    public long insertHabitQuestion(HabitQuestion habitQuestion) {
        habitQuestionMapper.insertHabitQuestion(habitQuestion);
        return habitQuestion.getId();
    }

    @Override
    public void updateHabitQuestion(HabitQuestion habitQuestion) {
        habitQuestionMapper.updateHabitQuestion(habitQuestion);
    }

    @Override
    public void deleteHabitQuestion(long id) {
        habitQuestionMapper.deleteHabitQuestion(id);
    }

    @Override
    public List<HabitQuestion> getHabitQuestionByTitle(String title) {
        return habitQuestionMapper.getHabitQuestionByTitle(title);
    }
}
