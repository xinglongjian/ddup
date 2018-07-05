package com.xingtan.habit.service.impl;

import com.xingtan.habit.bean.HabitQuestionData;
import com.xingtan.habit.bean.HabitQuestionRelationList;
import com.xingtan.habit.entity.Habit;
import com.xingtan.habit.entity.HabitQuestion;
import com.xingtan.habit.entity.HabitQuestionItem;
import com.xingtan.habit.entity.HabitQuestionRelation;
import com.xingtan.habit.mapper.HabitMapper;
import com.xingtan.habit.mapper.HabitQuestionItemMapper;
import com.xingtan.habit.mapper.HabitQuestionMapper;
import com.xingtan.habit.mapper.HabitQuestionRelationMapper;
import com.xingtan.habit.service.HabitQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xinglongjian@qq.com
 * @Date 2018/6/23/023 16:40
 */
@Service
public class HabitQuestionServiceImpl implements HabitQuestionService {

    @Autowired
    private HabitQuestionMapper habitQuestionMapper;
    @Autowired
    private HabitQuestionItemMapper habitQuestionItemMapper;
    @Autowired
    private HabitMapper habitMapper;
    @Autowired
    private HabitQuestionRelationMapper habitQuestionRelationMapper;

    @Override
    public HabitQuestion getHabitQuestionById(long id) {
        return habitQuestionMapper.getHabitQuestionById(id);
    }

    @Override
    public HabitQuestionData getHabitQuestionDataById(long id) {
        HabitQuestion question = habitQuestionMapper.getHabitQuestionById(id);
        HabitQuestionData data = new HabitQuestionData();
        if (question != null) {
            data.setQuestionId(id);
            data.setTitle(question.getTitle());
            List<HabitQuestionItem> items = habitQuestionItemMapper.getHabitItemByQuestionId(id);
            data.setItems(items);
        }
        return data;
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

    @Override
    public HabitQuestionRelationList getHabitRelationList(long habitId) {
        HabitQuestionRelationList relationList = new HabitQuestionRelationList();
        Habit habit = habitMapper.getHabitById(habitId);
        if (habit != null) {
            relationList.setHabitId(habitId);
            relationList.setTitle(habit.getName());
            List<HabitQuestionRelation> relations = habitQuestionRelationMapper.getRelationByHabitId(habitId);
            if (!CollectionUtils.isEmpty(relations)) {

                List<Long> questionIds = new ArrayList<>();
                for (HabitQuestionRelation r : relations) {
                    questionIds.add(r.getQuestionId());
                }

                List<HabitQuestion> questions = habitQuestionMapper.getHabitQuestionByIds(questionIds);
                relationList.setQuestions(questions);
            }
        }

        return relationList;
    }
}
