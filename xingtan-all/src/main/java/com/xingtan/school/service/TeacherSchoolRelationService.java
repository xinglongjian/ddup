package com.xingtan.school.service;

import com.xingtan.school.entity.TeacherSchoolRelation;

public interface TeacherSchoolRelationService {

    long insertRelation(TeacherSchoolRelation relation);
    void updateRelation(TeacherSchoolRelation relation);
    void deleteRelation(long id);
}
