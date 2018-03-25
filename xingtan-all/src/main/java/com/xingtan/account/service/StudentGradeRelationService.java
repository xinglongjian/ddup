package com.xingtan.account.service;

import com.xingtan.account.entity.StudentGradeRelation;

import java.util.List;

public interface StudentGradeRelationService {
    StudentGradeRelation getRelationById(long id);
    List<StudentGradeRelation> getRelationsByStudentId(long studentId);
    List<StudentGradeRelation> getRelationsByGradeId(long gradeId);
    long insertRelation(StudentGradeRelation relation);
    void updateRelation(StudentGradeRelation relation);
    void deleteRelation(long id);
}
