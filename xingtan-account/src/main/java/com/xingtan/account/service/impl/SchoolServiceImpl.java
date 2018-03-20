package com.xingtan.account.service.impl;

import com.xingtan.account.entity.School;
import com.xingtan.account.entity.TeacherSchoolRelation;
import com.xingtan.account.mapper.SchoolMapper;
import com.xingtan.account.mapper.TeacherSchoolRelationMapper;
import com.xingtan.account.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private TeacherSchoolRelationMapper teacherSchoolRelationMapper;

    @Override
    public School getSchoolById(long id) {
        return schoolMapper.getSchoolById(id);
    }

    @Override
    public List<School> getSchoolByName(String name) {
        return schoolMapper.getSchoolByName(name);
    }

    @Override
    public long insertSchool(School school) {
        schoolMapper.insertSchool(school);
        return school.getId();
    }

    @Override
    public void updateSchool(School school) {
        schoolMapper.updateSchool(school);
    }

    @Override
    public void deleteSchool(long id) {
        schoolMapper.deleteSchool(id);
    }

    @Override
    public long insertTeacherSchoolRelation(TeacherSchoolRelation relation) {
        teacherSchoolRelationMapper.insertRelation(relation);
        return relation.getId();
    }

    @Override
    public void updateTeacherSchoolRelation(TeacherSchoolRelation relation) {
        teacherSchoolRelationMapper.updateRelation(relation);
    }

    @Override
    public void deleteTeacherSchoolRelation(long id) {
        teacherSchoolRelationMapper.deleteRelation(id);
    }
}
