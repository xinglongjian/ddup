package com.xingtan.school.mapper;

import com.xingtan.school.entity.TeacherGradeRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeacherGradeRelationMapper {

    TeacherGradeRelation getRelationById(@Param("id") long id);

    List<TeacherGradeRelation> getRelationByTeacherId(@Param("teacherId") long teacherId);

    List<TeacherGradeRelation> getRelationsByGradeId(@Param("gradeId") long gradeId);

    void insertRelation(TeacherGradeRelation relation);

    void updateRelation(TeacherGradeRelation relation);

    void deleteRelation(@Param("id") long id);

}
