package com.xingtan.account.service.impl;

import com.xingtan.account.entity.TeacherGradeRelation;
import com.xingtan.account.mapper.TeacherGradeRelationMapper;
import com.xingtan.account.service.TeacherGradeRelationService;
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
