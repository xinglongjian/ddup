package com.xingtan.habit.service.impl;

import com.xingtan.habit.entity.HabitQuestionItem;
import com.xingtan.habit.mapper.HabitQuestionItemMapper;
import com.xingtan.habit.service.HabitQuestionItemService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xinglongjian@qq.com
 * @Date 2018/6/23/023 16:32
 */
@Service
public class HabitQuestionItemServiceImpl implements HabitQuestionItemService {

    @Autowired
    private HabitQuestionItemMapper habitQuestionItemMapper;

    @Override
    public HabitQuestionItem getHabitItemById(long id) {
        return habitQuestionItemMapper.getHabitItemById(id);
    }

    @Override
    public List<HabitQuestionItem> getHabitItemByQuestionId(long questionId) {
        return habitQuestionItemMapper.getHabitItemByQuestionId(questionId);
    }

    @Override
    public List<HabitQuestionItem> getHabitItemByIds(List<Long> ids) {
        return habitQuestionItemMapper.getHabitItemByIds(ids);
    }

    @Override
    public long insertHabitItem(HabitQuestionItem habitQuestionItem) {
        habitQuestionItemMapper.insertHabitItem(habitQuestionItem);
        return habitQuestionItem.getId();
    }

    @Override
    public void updateHabitItem(HabitQuestionItem habitQuestionItem) {
        habitQuestionItemMapper.updateHabitItem(habitQuestionItem);
    }

    @Override
    public void deleteHabitItem(long id) {
        habitQuestionItemMapper.deleteHabitItem(id);
    }
}
