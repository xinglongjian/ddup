package com.xingtan.habit.service;

import com.xingtan.habit.bean.HabitData;
import com.xingtan.habit.entity.Habit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HabitService {
    List<Habit> getAll();
    List<Habit> getHabitsByUserId(long userId);
    List<HabitData> getMostHabitsByLimit(int start, int num);
    Habit getHabitById(long id);
    List<Habit> getHabitByTypeId(long habitTypeId);
    long insertHabit(Habit habit);
    void updateHabit(Habit habit);
    void deleteHabit(long id);
}
