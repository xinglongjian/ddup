package com.xingtan.account.service.impl;

import com.xingtan.account.entity.StudentParentRelation;
import com.xingtan.account.mapper.StudentParentRelationMapper;
import com.xingtan.account.service.StudentParentRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentParentRelationServiceImpl implements StudentParentRelationService {

    @Autowired
    private StudentParentRelationMapper studentParentRelationMapper;

    @Override
    public List<StudentParentRelation> getRelationsByStudentId(long studentId) {
        return studentParentRelationMapper.getRelationsByStudentId(studentId);
    }

    @Override
    public List<StudentParentRelation> getRelationsByParentId(long parentId) {
        return studentParentRelationMapper.getRelationsByParentId(parentId);
    }

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
