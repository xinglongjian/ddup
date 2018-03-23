package com.xingtan.habit.mapper;

import com.xingtan.habit.entity.HabitType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HabitTypeMapper {
    List<HabitType> getAll();
    HabitType getHabitTypeById(@Param("id") long id);
    void insertHabitType(HabitType habitType);
    void updateHabitType(HabitType habitType);
    void deleteHabitType(@Param("id") long id);
}
