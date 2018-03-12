package com.xingtan.choose.service.impl;

import com.xingtan.choose.entity.StudentClassesRelation;
import com.xingtan.choose.mapper.StudentClassesRelationMapper;
import com.xingtan.choose.service.StudentClassesRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentClassesRelationServiceImpl implements StudentClassesRelationService {

    @Autowired
    private StudentClassesRelationMapper studentClassesRelationMapper;

    @Override
    public StudentClassesRelation getStudentClassesRelationById(long id) {
        return studentClassesRelationMapper.getStudentClassesRelationById(id);
    }

    @Override
    public List<StudentClassesRelation> getStudentClassesRelationByStudentId(long studentId) {
        return studentClassesRelationMapper.getStudentClassesRelationByStudentId(studentId);
    }

    @Override
    public List<StudentClassesRelation> getStudentClassesRelationByClassesId(long classesId) {
        return studentClassesRelationMapper.getStudentClassesRelationByClassesId(classesId);
    }

    @Override
    public long insertStudentClassesRelation(StudentClassesRelation studentClassesRelation) {
        studentClassesRelationMapper.insertStudentClassesRelation(studentClassesRelation);
        return studentClassesRelation.getId();
    }

    @Override
    public void updateStudentClassesRelation(StudentClassesRelation studentClassesRelation) {
        studentClassesRelationMapper.updateStudentClassesRelation(studentClassesRelation);
    }

    @Override
    public void deleteStudentClassesRelation(long id) {
        studentClassesRelationMapper.deleteStudentClassesRelation(id);
    }
}
