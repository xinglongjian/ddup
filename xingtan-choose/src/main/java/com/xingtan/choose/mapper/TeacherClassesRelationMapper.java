package com.xingtan.choose.mapper;

import com.xingtan.choose.entity.TeacherClassesRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeacherClassesRelationMapper {

    TeacherClassesRelation getTeacherClassesRelationById(@Param("id") long id);
    List<TeacherClassesRelation> getTeacherClassesRelationByTeacherId(@Param("teacherId") long teacherId);
    List<TeacherClassesRelation> getTeacherClassesRelationByClassesId(@Param("classesId") long classesId);
    void insertTeacherClassesRelation(TeacherClassesRelation teacherClassesRelation);
    void updateTeacherClassesRelation(TeacherClassesRelation teacherClassesRelation);
    void deleteTeacherClassesRelation(@Param("id") long id);
}
