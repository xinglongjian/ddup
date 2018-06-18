package com.xingtan.habit.service.impl;

import com.xingtan.habit.bean.HabitData;
import com.xingtan.habit.entity.Habit;
import com.xingtan.habit.entity.UserHabitRecord;
import com.xingtan.habit.entity.UserHabitRelation;
import com.xingtan.habit.mapper.HabitMapper;
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
    @Override
    public List<Habit> getAll() {
        return habitMapper.getAll();
    }

    @Override
    public List<Habit> getHabitsByUserId(long userId) {
        List<UserHabitRelation> userHabitRelations = userHabitRelationMapper.getRecordsByUserId(userId);
        if(CollectionUtils.isEmpty(userHabitRelations)) {
            return new ArrayList<>();
        } else  {

            List<Long> habitIds = new ArrayList<>();
            for(UserHabitRelation relation: userHabitRelations) {
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
}
