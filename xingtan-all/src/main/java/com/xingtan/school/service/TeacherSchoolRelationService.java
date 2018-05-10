package com.xingtan.school.service;

import com.xingtan.school.entity.TeacherGradeRelation;
import com.xingtan.school.entity.TeacherSchoolRelation;

import java.util.List;

public interface TeacherSchoolRelationService {
    List<TeacherSchoolRelation> getRelationsByTeacherId(long teacherId);
    long insertRelation(TeacherSchoolRelation relation);
    void updateRelation(TeacherSchoolRelation relation);
    void deleteRelation(long id);
}
