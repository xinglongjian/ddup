package com.xingtan.habit.service.impl;

import com.xingtan.habit.entity.Habit;
import com.xingtan.habit.mapper.HabitMapper;
import com.xingtan.habit.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitServiceImpl implements HabitService {

    @Autowired
    private HabitMapper habitMapper;
    @Override
    public List<Habit> getAll() {
        return habitMapper.getAll();
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
