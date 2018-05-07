package com.xingtan.school.service.impl;

import com.xingtan.school.entity.StudentSchoolRelation;
import com.xingtan.school.mapper.StudentSchoolRelationMapper;
import com.xingtan.school.service.StudentSchoolRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xinglongjian on 5/6 2018 22:40.
 */
@Service
public class StudentSchoolRelationServiceImpl implements StudentSchoolRelationService {

    @Autowired
    private StudentSchoolRelationMapper studentSchoolRelationMapper;

    @Override
    public StudentSchoolRelation getRelationById(long id) {
        return studentSchoolRelationMapper.getRelationById(id);
    }

    @Override
    public List<StudentSchoolRelation> getRelationsByStudentId(long studentId) {
        return studentSchoolRelationMapper.getRelationsByStudentId(studentId);
    }

    @Override
    public List<StudentSchoolRelation> getRelationsBySchoolId(long schoolId) {
        return studentSchoolRelationMapper.getRelationsBySchoolId(schoolId);
    }

    @Override
    public long insertRelation(StudentSchoolRelation relation) {
        studentSchoolRelationMapper.insertRelation(relation);
        return relation.getId();
    }

    @Override
    public void updateRelation(StudentSchoolRelation relation) {
        studentSchoolRelationMapper.updateRelation(relation);
    }

    @Override
    public void deleteRelation(long id) {
        studentSchoolRelationMapper.deleteRelation(id);
    }
}
