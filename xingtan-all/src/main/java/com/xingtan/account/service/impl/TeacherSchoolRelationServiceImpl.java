package com.xingtan.account.service.impl;

import com.xingtan.account.entity.TeacherSchoolRelation;
import com.xingtan.account.mapper.TeacherSchoolRelationMapper;
import com.xingtan.account.service.TeacherSchoolRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherSchoolRelationServiceImpl implements TeacherSchoolRelationService {

    @Autowired
    private TeacherSchoolRelationMapper teacherSchoolRelationMapper;

    @Override
    public long insertRelation(TeacherSchoolRelation relation) {
        teacherSchoolRelationMapper.insertRelation(relation);
        return relation.getId();
    }

    @Override
    public void updateRelation(TeacherSchoolRelation relation) {
        teacherSchoolRelationMapper.updateRelation(relation);
    }

    @Override
    public void deleteRelation(long id) {
        teacherSchoolRelationMapper.deleteRelation(id);
    }
}
