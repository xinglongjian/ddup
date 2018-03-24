package com.xingtan.habit.service;

import com.xingtan.habit.entity.Habit;

import java.util.List;

public interface HabitService {
    List<Habit> getAll();
    Habit getHabitById(long id);
    List<Habit> getHabitByTypeId(long habitTypeId);
    long insertHabit(Habit habit);
    void updateHabit(Habit habit);
    void deleteHabit(long id);
}
