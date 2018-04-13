package com.xingtan.school.service;

import com.xingtan.school.entity.TeacherGradeRelation;

import java.util.List;

public interface TeacherGradeRelationService {
    TeacherGradeRelation getRelationById(long id);
    List<TeacherGradeRelation> getRelationByTeacherId(long teacherId);
    List<TeacherGradeRelation> getRelationsByGradeId(long gradeId);
    long insertRelation(TeacherGradeRelation relation);
    void updateRelation(TeacherGradeRelation relation);
    void deleteRelation(long id);
}
