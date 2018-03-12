package com.xingtan.choose.service;

import com.xingtan.choose.entity.StudentClassesRelation;

import java.util.List;

public interface StudentClassesRelationService {
    StudentClassesRelation getStudentClassesRelationById(long id);
    List<StudentClassesRelation> getStudentClassesRelationByStudentId(long studentId);
    List<StudentClassesRelation> getStudentClassesRelationByClassesId(long classesId);
    long insertStudentClassesRelation(StudentClassesRelation studentClassesRelation);
    void updateStudentClassesRelation(StudentClassesRelation studentClassesRelation);
    void deleteStudentClassesRelation(long id);
}
