package com.xingtan.school.mapper;

import com.xingtan.school.entity.TeacherSchoolRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeacherSchoolRelationMapper {

    TeacherSchoolRelation getRelationById(@Param("id") long id);

    List<TeacherSchoolRelation> getRelationsByTeacherId(@Param("teacherId") long teacherId);

    List<TeacherSchoolRelation> getRelationsBySchoolId(@Param("schoolId") long schoolId);

    void insertRelation(TeacherSchoolRelation relation);

    void updateRelation(TeacherSchoolRelation relation);

    void deleteRelation(@Param("id") long id);
}
