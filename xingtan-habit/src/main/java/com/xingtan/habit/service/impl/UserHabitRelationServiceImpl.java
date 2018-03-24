package com.xingtan.habit.service.impl;

import com.xingtan.habit.entity.UserHabitRelation;
import com.xingtan.habit.mapper.UserHabitRelationMapper;
import com.xingtan.habit.service.UserHabitRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHabitRelationServiceImpl implements UserHabitRelationService {

    @Autowired
    private UserHabitRelationMapper userHabitRelationMapper;

    @Override
    public UserHabitRelation getRelationById(long id) {
        return userHabitRelationMapper.getRelationById(id);
    }

    @Override
    public List<UserHabitRelation> getRecordsByUserId(long userId) {
        return userHabitRelationMapper.getRecordsByUserId(userId);
    }

    @Override
    public long insertRecord(UserHabitRelation userHabitRelation) {
        userHabitRelationMapper.insertRecord(userHabitRelation);
        return userHabitRelation.getId();
    }

    @Override
    public void updateRecord(UserHabitRelation userHabitRelation) {
        userHabitRelationMapper.updateRecord(userHabitRelation);
    }

    @Override
    public void deleteRecord(long id) {
        userHabitRelationMapper.deleteRecord(id);
    }
}
