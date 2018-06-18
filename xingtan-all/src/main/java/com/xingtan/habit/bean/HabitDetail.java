package com.xingtan.habit.bean;

import com.xingtan.habit.entity.Habit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class HabitDetail extends HabitData {

    public HabitDetail(Habit habit) {
        setId(habit.getId());
        setAgeEnd(habit.getAgeEnd());
        setAgeStart(habit.getAgeStart());
        setDescription(habit.getDescription());
        setHabitTypeId(habit.getHabitTypeId());
        setImage(habit.getImage());
        setName(habit.getName());
        setSexScope(habit.getSexScope());
    }

}
