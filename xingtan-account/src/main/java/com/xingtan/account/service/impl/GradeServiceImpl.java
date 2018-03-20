package com.xingtan.account.service.impl;

import com.xingtan.account.entity.Grade;
import com.xingtan.account.entity.TeacherGradeRelation;
import com.xingtan.account.mapper.GradeMapper;
import com.xingtan.account.mapper.TeacherGradeRelationMapper;
import com.xingtan.account.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private TeacherGradeRelationMapper teacherGradeRelationMapper;

    @Override
    public Grade getGradeById(long id) {
        return gradeMapper.getGradeById(id);
    }

    @Override
    public List<Grade> getGradesBySchoolId(long schoolId) {
        return gradeMapper.getGradesBySchoolId(schoolId);
    }

    @Override
    public List<Grade> getGradeByName(String name) {
        return gradeMapper.getGradeByName(name);
    }

    @Override
    public long insertGrade(Grade grade) {
        gradeMapper.insertGrade(grade);
        return grade.getId();
    }

    @Override
    public void updateGrade(Grade grade) {
        gradeMapper.updateGrade(grade);
    }

    @Override
    public void deleteGrade(long id) {
        gradeMapper.deleteGrade(id);
    }

    @Override
    public long insertTeacherGradeRelation(TeacherGradeRelation relation) {
        teacherGradeRelationMapper.insertRelation(relation);
        return relation.getId();
    }
    @Override
    public void updateTeacherGradeRelation(TeacherGradeRelation relation) {
        teacherGradeRelationMapper.updateRelation(relation);
    }

    @Override
    public void deleteTeacherGradeRelation(long id) {
        teacherGradeRelationMapper.deleteRelation(id);
    }
}
