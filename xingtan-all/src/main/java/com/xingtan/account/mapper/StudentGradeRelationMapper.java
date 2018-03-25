package com.xingtan.account.mapper;

import com.xingtan.account.entity.StudentGradeRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentGradeRelationMapper {

    StudentGradeRelation getRelationById(@Param("id") long id);

    List<StudentGradeRelation> getRelationsByStudentId(@Param("studentId") long studentId);

    List<StudentGradeRelation> getRelationsByGradeId(@Param("gradeId") long gradeId);

    void insertRelation(StudentGradeRelation relation);

    void updateRelation(StudentGradeRelation relation);

    void deleteRelation(@Param("id") long id);


}
