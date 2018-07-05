package com.xingtan.habit.service.impl;

import com.xingtan.common.entity.PageEntity;
import com.xingtan.habit.bean.HabitData;
import com.xingtan.habit.bean.HabitQuestionData;
import com.xingtan.habit.bean.HabitQuestionRelationData;
import com.xingtan.habit.entity.Habit;
import com.xingtan.habit.entity.UserHabitRecord;
import com.xingtan.habit.entity.UserHabitRelation;
import com.xingtan.habit.mapper.HabitMapper;
import com.xingtan.habit.mapper.HabitQuestionRelationMapper;
import com.xingtan.habit.mapper.UserHabitRelationMapper;
import com.xingtan.habit.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class HabitServiceImpl implements HabitService {

    @Autowired
    private HabitMapper habitMapper;
    @Autowired
    private UserHabitRelationMapper userHabitRelationMapper;
    @Autowired
    private HabitQuestionRelationMapper habitQuestionRelationMapper;

    @Override
    public List<Habit> getAll() {
        return habitMapper.getAll();
    }

    @Override
    public List<Habit> getHabitsByUserId(long userId) {
        List<UserHabitRelation> userHabitRelations = userHabitRelationMapper.getRecordsByUserId(userId);
        if (CollectionUtils.isEmpty(userHabitRelations)) {
            return new ArrayList<>();
        } else {

            List<Long> habitIds = new ArrayList<>();
            for (UserHabitRelation relation : userHabitRelations) {
                habitIds.add(relation.getHabitId());
            }
            return habitMapper.getHabitsByIds(habitIds);
        }
    }

    @Override
    public List<HabitData> getMostHabitsByLimit(int start, int num) {
        return habitMapper.getMostHabitsByLimit(start, num);
    }

    @Override
    public Habit getHabitById(long id) {
        return habitMapper.getHabitById(id);
    }

    @Override
    public List<Habit> getHabitByTypeId(long habitTypeId) {
        return habitMapper.getHabitByTypeId(habitTypeId);
    }

    @Override
    public long insertHabit(Habit habit) {
        habitMapper.insertHabit(habit);
        return habit.getId();
    }

    @Override
    public void updateHabit(Habit habit) {
        habitMapper.updateHabit(habit);
    }

    @Override
    public void deleteHabit(long id) {
        habitMapper.deleteHabit(id);
    }

    @Override
    public PageEntity getPageEntity(long typeId, String name, int pageNum, int pageSize) {

        PageEntity page = new PageEntity();
        long allCount = habitMapper.getCountByTypeIdAndName(typeId, name);
        page.setAllCount(allCount);
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        List<Habit> habits = habitMapper.getHabitByTypeIdAndName(typeId, name, (pageNum - 1) * pageSize, pageSize);
        List<HabitQuestionRelationData> datas = new ArrayList<>();
        if (!CollectionUtils.isEmpty(habits)) {
            for (Habit habit : habits) {
                int count = habitQuestionRelationMapper.getRelationCountByHabitId(habit.getId());
                HabitQuestionRelationData data = new HabitQuestionRelationData();
                data.setHabit(habit);
                data.setRelationCount(count);
                datas.add(data);
            }
        }
        page.setDatas(datas);
        return page;
    }
}
