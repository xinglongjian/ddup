package com.xingtan.account.service;

import com.xingtan.account.entity.StudentParentRelation;

import java.util.List;

public interface StudentParentRelationService {
    List<StudentParentRelation> getRelationsByStudentId(long studentId);
    List<StudentParentRelation> getRelationsByParentId(long parentId);
    long insertRelation(StudentParentRelation relation);
    void updateRelation(StudentParentRelation relation);
    void deleteRelation(long id);
}
