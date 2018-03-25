package com.xingtan.account.service;

import com.xingtan.account.entity.StudentParentRelation;

public interface StudentParentRelationService {
    long insertRelation(StudentParentRelation relation);
    void updateRelation(StudentParentRelation relation);
    void deleteRelation(long id);
}
