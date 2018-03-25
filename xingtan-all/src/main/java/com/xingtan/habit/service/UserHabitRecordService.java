package com.xingtan.habit.service;

import com.xingtan.habit.entity.UserHabitRecord;

import java.util.List;

public interface UserHabitRecordService {
    UserHabitRecord getRecordById(long id);
    List<UserHabitRecord> getRecordsByUserId(long userId);
    long insertRecord(UserHabitRecord userHabitRecord);
    void updateRecord(UserHabitRecord userHabitRecord);
    void deleteRecord(long id);
}
