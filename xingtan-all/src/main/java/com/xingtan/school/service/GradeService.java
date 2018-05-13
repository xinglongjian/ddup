package com.xingtan.school.service;

import com.xingtan.school.entity.Grade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GradeService {
    Grade getGradeById(long id);
    List<Grade> getGradesByIds(List<Long> ids);
    List<Grade> getGradesBySchoolId(long schoolId);
    List<Grade> getGradeByName(String name);
    long insertGrade(Grade grade);
    void updateGrade(Grade grade);
    void deleteGrade(long id);

}
