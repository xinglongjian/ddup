package com.xingtan.account.mapper;

import com.xingtan.account.entity.StudentParentRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentParentRelationMapper {
    
    StudentParentRelation getRelationById(@Param("id") long id);

    List<StudentParentRelation> getRelationsByStudentId(@Param("studentId") long studentId);

    List<StudentParentRelation> getRelationsByParentId(@Param("parentId") long parentId);

    void insertRelation(StudentParentRelation relation);

    void updateRelation(StudentParentRelation relation);

    void deleteRelation(@Param("id") long id);

}
