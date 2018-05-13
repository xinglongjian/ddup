package com.xingtan.school.service;

import com.xingtan.school.bean.GradeData;
import com.xingtan.school.bean.GradeItemData;
import com.xingtan.school.entity.Grade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GradeService {
    Grade getGradeById(long id);
    GradeData getGradeDataById(long id);
    GradeItemData getGradeItemData(long id);
    List<Grade> getGradesByIds(List<Long> ids);
    List<Grade> getGradesBySchoolId(long schoolId);
    List<Grade> getGradeByName(String name);
    long insertGrade(Grade grade);
    void updateGrade(Grade grade);
    void deleteGrade(long id);

}
