package com.xingtan.choose.mapper;

import com.xingtan.choose.entity.StudentClassesRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentClassesRelationMapper {

    StudentClassesRelation getStudentClassesRelationById(@Param("id") long id);
    List<StudentClassesRelation> getStudentClassesRelationByStudentId(@Param("studentId") long studentId);
    List<StudentClassesRelation> getStudentClassesRelationByClassesId(@Param("classesId") long classesId);
    void insertStudentClassesRelation(StudentClassesRelation studentClassesRelation);
    void updateStudentClassesRelation(StudentClassesRelation studentClassesRelation);
    void deleteStudentClassesRelation(@Param("id") long id);
}
