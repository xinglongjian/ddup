package com.xingtan.account.service.impl;

import com.xingtan.account.entity.StudentParentRelation;
import com.xingtan.account.mapper.StudentParentRelationMapper;
import com.xingtan.account.service.StudentParentRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentParentRelationServiceImpl implements StudentParentRelationService {

    @Autowired
    private StudentParentRelationMapper studentParentRelationMapper;

    @Override
    public long insertRelation(StudentParentRelation relation) {
        studentParentRelationMapper.insertRelation(relation);
        return relation.getId();
    }

    @Override
    public void updateRelation(StudentParentRelation relation) {
        studentParentRelationMapper.updateRelation(relation);
    }

    @Override
    public void deleteRelation(long id) {
        studentParentRelationMapper.deleteRelation(id);
    }
}
