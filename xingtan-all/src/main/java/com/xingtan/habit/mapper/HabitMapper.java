package com.xingtan.habit.mapper;

import com.xingtan.habit.bean.HabitData;
import com.xingtan.habit.entity.Habit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HabitMapper {

    List<Habit> getAll();

    List<HabitData> getMostHabitsByLimit(@Param("start") int start,
                                         @Param("num") int num);

    Habit getHabitById(@Param("id") long id);

    List<Habit> getHabitsByIds(@Param("ids") List<Long> ids);

    List<Habit> getHabitByTypeId(@Param("habitTypeId") long habitTypeId);

    void insertHabit(Habit habit);

    void updateHabit(Habit habit);

    void deleteHabit(@Param("id") long id);
}
