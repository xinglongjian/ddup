package com.xingtan.choose.service.impl;

import com.xingtan.choose.entity.TeacherClassesRelation;
import com.xingtan.choose.mapper.TeacherClassesRelationMapper;
import com.xingtan.choose.service.TeacherClassesRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TeacherClassesRelationServiceImpl implements TeacherClassesRelationService {

    @Autowired
    private TeacherClassesRelationMapper teacherClassesRelationMapper;

    @Override
    public TeacherClassesRelation getTeacherClassesRelationById(long id) {
        return teacherClassesRelationMapper.getTeacherClassesRelationById(id);
    }

    @Override
    public List<TeacherClassesRelation> getTeacherClassesRelationByTeacherId(long teacherId) {
        return teacherClassesRelationMapper.getTeacherClassesRelationByTeacherId(teacherId);
    }

    @Override
    public List<TeacherClassesRelation> getTeacherClassesRelationByClassesId(long classesId) {
        return teacherClassesRelationMapper.getTeacherClassesRelationByClassesId(classesId);
    }

    @Override
    public long insertTeacherClassesRelation(TeacherClassesRelation teacherClassesRelation) {
        teacherClassesRelationMapper.insertTeacherClassesRelation(teacherClassesRelation);
        return teacherClassesRelation.getId();
    }

    @Override
    public void updateTeacherClassesRelation(TeacherClassesRelation teacherClassesRelation) {
        teacherClassesRelationMapper.updateTeacherClassesRelation(teacherClassesRelation);
    }

    @Override
    public void deleteTeacherClassesRelation(long id) {
        teacherClassesRelationMapper.deleteTeacherClassesRelation(id);
    }
}
