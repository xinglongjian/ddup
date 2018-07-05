package com.xingtan.habit.service;

import com.xingtan.habit.bean.HabitQuestionData;
import com.xingtan.habit.bean.HabitQuestionRelationList;
import com.xingtan.habit.entity.HabitQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author xinglongjian@qq.com
 * @Date 2018/6/23/ 15:49
 */
public interface HabitQuestionService {
    HabitQuestion getHabitQuestionById(long id);
    HabitQuestionData getHabitQuestionDataById(long id);
    List<HabitQuestion> getHabitQuestionByIds(List<Long> ids);
    long insertHabitQuestion(HabitQuestion habitQuestion);
    void updateHabitQuestion(HabitQuestion habitQuestion);
    void deleteHabitQuestion(long id);
    List<HabitQuestion> getHabitQuestionByTitle(String title);
    HabitQuestionRelationList getHabitRelationList(long habitId);
}
