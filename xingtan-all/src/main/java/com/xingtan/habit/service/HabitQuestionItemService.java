package com.xingtan.habit.service;

import com.xingtan.habit.entity.HabitQuestionItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author xinglongjian@qq.com
 * @Date 2018/6/23/023 15:51
 */
public interface HabitQuestionItemService {
    HabitQuestionItem getHabitItemById(long id);
    List<HabitQuestionItem> getHabitItemByQuestionId(long questionId);
    List<HabitQuestionItem> getHabitItemByIds(List<Long> ids);
    long insertHabitItem(HabitQuestionItem habitQuestionItem);
    void updateHabitItem(HabitQuestionItem habitQuestionItem);
    void deleteHabitItem(long id);
}
