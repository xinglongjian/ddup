package com.xingtan.habit.service;

import com.xingtan.habit.entity.HabitType;

import java.util.List;

public interface HabitTypeService {
    List<HabitType> getAll();
    HabitType getHabitTypeById(long id);
    long insertHabitType(HabitType habitType);
    void updateHabitType(HabitType habitType);
    void deleteHabitType(long id);
}
