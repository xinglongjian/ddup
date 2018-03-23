package com.xingtan.habit.mapper;

import com.xingtan.habit.entity.UserHabitRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserHabitRecordMapper {
    UserHabitRecord getRecordById(@Param("id") long id);
    List<UserHabitRecord> getRecordsByUserId(@Param("userId") long userId);
    void insertRecord(UserHabitRecord userHabitRecord);
    void updateRecord(UserHabitRecord userHabitRecord);
    void deleteRecord(@Param("id") long id);
}
