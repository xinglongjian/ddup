package com.xingtan.school.service;

import com.xingtan.school.entity.StudentSchoolRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xinglongjian on 5/6 0006 22:39.
 */
public interface StudentSchoolRelationService {

    StudentSchoolRelation getRelationById(long id);

    List<StudentSchoolRelation> getRelationsByStudentId(long studentId);

    List<StudentSchoolRelation> getRelationsBySchoolId(long schoolId);

    long insertRelation(StudentSchoolRelation relation);

    void updateRelation(StudentSchoolRelation relation);

    void deleteRelation(long id);
}
