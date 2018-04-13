package com.xingtan.school.service.impl;

import com.xingtan.school.entity.TeacherGradeRelation;
import com.xingtan.school.mapper.TeacherGradeRelationMapper;
import com.xingtan.school.service.TeacherGradeRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherGradeRelationServiceImpl implements TeacherGradeRelationService {

    @Autowired
    private TeacherGradeRelationMapper teacherGradeRelationMapper;

    @Override
    public TeacherGradeRelation getRelationById(long id) {
        return teacherGradeRelationMapper.getRelationById(id);
    }

    @Override
    public List<TeacherGradeRelation> getRelationByTeacherId(long teacherId) {
        return teacherGradeRelationMapper.getRelationByTeacherId(teacherId);
    }

    @Override
    public List<TeacherGradeRelation> getRelationsByGradeId(long gradeId) {
        return teacherGradeRelationMapper.getRelationsByGradeId(gradeId);
    }

    @Override
    public long insertRelation(TeacherGradeRelation relation) {
        teacherGradeRelationMapper.insertRelation(relation);
        return relation.getId();
    }

    @Override
    public void updateRelation(TeacherGradeRelation relation) {
        teacherGradeRelationMapper.updateRelation(relation);
    }

    @Override
    public void deleteRelation(long id) {
        teacherGradeRelationMapper.deleteRelation(id);
    }
}
