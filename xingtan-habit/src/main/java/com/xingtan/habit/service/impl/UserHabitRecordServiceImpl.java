package com.xingtan.habit.service.impl;

import com.xingtan.habit.entity.UserHabitRecord;
import com.xingtan.habit.mapper.UserHabitRecordMapper;
import com.xingtan.habit.service.UserHabitRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHabitRecordServiceImpl implements UserHabitRecordService {

    @Autowired
    private UserHabitRecordMapper userHabitRecordMapper;
    @Override
    public UserHabitRecord getRecordById(long id) {
        return userHabitRecordMapper.getRecordById(id);
    }

    @Override
    public List<UserHabitRecord> getRecordsByUserId(long userId) {
        return userHabitRecordMapper.getRecordsByUserId(userId);
    }

    @Override
    public long insertRecord(UserHabitRecord userHabitRecord) {
        userHabitRecordMapper.insertRecord(userHabitRecord);
        return userHabitRecord.getId();
    }

    @Override
    public void updateRecord(UserHabitRecord userHabitRecord) {
        userHabitRecordMapper.updateRecord(userHabitRecord);
    }

    @Override
    public void deleteRecord(long id) {
        userHabitRecordMapper.deleteRecord(id);
    }
}
