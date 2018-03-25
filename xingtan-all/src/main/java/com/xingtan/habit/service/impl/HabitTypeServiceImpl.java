package com.xingtan.habit.service.impl;

import com.xingtan.habit.entity.HabitType;
import com.xingtan.habit.mapper.HabitTypeMapper;
import com.xingtan.habit.service.HabitTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitTypeServiceImpl implements HabitTypeService {

    @Autowired
    private HabitTypeMapper habitTypeMapper;

    @Override
    public List<HabitType> getAll() {
        return habitTypeMapper.getAll();
    }

    @Override
    public HabitType getHabitTypeById(long id) {
        return habitTypeMapper.getHabitTypeById(id);
    }

    @Override
    public long insertHabitType(HabitType habitType) {
        habitTypeMapper.insertHabitType(habitType);
        return habitType.getId();
    }

    @Override
    public void updateHabitType(HabitType habitType) {
        habitTypeMapper.updateHabitType(habitType);
    }

    @Override
    public void deleteHabitType(long id) {
        habitTypeMapper.deleteHabitType(id);
    }
}
