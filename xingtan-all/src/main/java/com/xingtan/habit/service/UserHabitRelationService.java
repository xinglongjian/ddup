package com.xingtan.habit.service;

import com.xingtan.habit.entity.UserHabitRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserHabitRelationService {
    UserHabitRelation getRelationById(long id);
    List<UserHabitRelation> getRecordsByUserId(long userId);
    long getCountOfHabit(long habitId);
    long insertRecord(UserHabitRelation userHabitRelation);
    void insertBatchRelations(List<UserHabitRelation> list);
    void updateRecord(UserHabitRelation userHabitRelation);
    void deleteRecord(long id);
    void deleteBatchRecord(List<Long> ids);
}
