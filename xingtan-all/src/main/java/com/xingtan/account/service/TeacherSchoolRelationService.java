package com.xingtan.account.service;

import com.xingtan.account.entity.TeacherSchoolRelation;

public interface TeacherSchoolRelationService {

    long insertRelation(TeacherSchoolRelation relation);
    void updateRelation(TeacherSchoolRelation relation);
    void deleteRelation(long id);
}
