package com.xingtan.choose.service;

import com.xingtan.choose.entity.TeacherClassesRelation;

import java.util.List;

public interface TeacherClassesRelationService {
    TeacherClassesRelation getTeacherClassesRelationById(long id);
    List<TeacherClassesRelation> getTeacherClassesRelationByTeacherId(long teacherId);
    List<TeacherClassesRelation> getTeacherClassesRelationByClassesId(long classesId);
    long insertTeacherClassesRelation(TeacherClassesRelation teacherClassesRelation);
    void updateTeacherClassesRelation(TeacherClassesRelation teacherClassesRelation);
    void deleteTeacherClassesRelation(long id);
}
