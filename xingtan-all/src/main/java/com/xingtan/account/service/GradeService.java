package com.xingtan.account.service;

import com.xingtan.account.entity.Grade;

import java.util.List;

public interface GradeService {
    Grade getGradeById(long id);
    List<Grade> getGradesBySchoolId(long schoolId);
    List<Grade> getGradeByName(String name);
    long insertGrade(Grade grade);
    void updateGrade(Grade grade);
    void deleteGrade(long id);

}
