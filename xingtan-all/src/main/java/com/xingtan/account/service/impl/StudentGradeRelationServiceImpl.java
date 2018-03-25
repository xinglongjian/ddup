package com.xingtan.account.service.impl;

import com.xingtan.account.entity.StudentGradeRelation;
import com.xingtan.account.mapper.StudentGradeRelationMapper;
import com.xingtan.account.service.StudentGradeRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentGradeRelationServiceImpl implements StudentGradeRelationService {

    @Autowired
    private StudentGradeRelationMapper studentGradeRelationMapper;

    @Override
    public StudentGradeRelation getRelationById(long id) {
        return studentGradeRelationMapper.getRelationById(id);
    }

    @Override
    public List<StudentGradeRelation> getRelationsByStudentId(long studentId) {
        return studentGradeRelationMapper.getRelationsByStudentId(studentId);
    }

    @Override
    public List<StudentGradeRelation> getRelationsByGradeId(long gradeId) {
        return studentGradeRelationMapper.getRelationsByGradeId(gradeId);
    }

    @Override
    public long insertRelation(StudentGradeRelation relation) {
        studentGradeRelationMapper.insertRelation(relation);
        return relation.getId();
    }

    @Override
    public void updateRelation(StudentGradeRelation relation) {
        studentGradeRelationMapper.updateRelation(relation);
    }

    @Override
    public void deleteRelation(long id) {
        studentGradeRelationMapper.deleteRelation(id);
    }


}
