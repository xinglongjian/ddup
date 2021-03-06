package com.xingtan.habit.service;

import com.xingtan.habit.entity.UserHabitRelation;

import java.util.List;

public interface UserHabitRelationService {
    UserHabitRelation getRelationById(long id);
    List<UserHabitRelation> getRecordsByUserId(long userId);
    long insertRecord(UserHabitRelation userHabitRelation);
    void updateRecord(UserHabitRelation userHabitRelation);
    void deleteRecord(long id);
}
