package com.xingtan.school.service.impl;

import com.xingtan.school.entity.TeacherSchoolRelation;
import com.xingtan.school.mapper.TeacherSchoolRelationMapper;
import com.xingtan.school.service.TeacherSchoolRelationService;
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
