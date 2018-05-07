package com.xingtan.school.mapper;

import com.xingtan.school.entity.StudentSchoolRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xinglongjian on 5/6 0006 22:39.
 */
@Mapper
public interface StudentSchoolRelationMapper {

    StudentSchoolRelation getRelationById(@Param("id") long id);

    List<StudentSchoolRelation> getRelationsByStudentId(@Param("studentId") long studentId);

    List<StudentSchoolRelation> getRelationsBySchoolId(@Param("schoolId") long schoolId);

    void insertRelation(StudentSchoolRelation relation);

    void updateRelation(StudentSchoolRelation relation);

    void deleteRelation(@Param("id") long id);
}
