package com.xingtan.account.service.impl;

import com.xingtan.account.entity.Grade;
import com.xingtan.account.mapper.GradeMapper;
import com.xingtan.account.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public Grade getGradeById(long id) {
        return gradeMapper.getGradeById(id);
    }

    @Override
    public List<Grade> getGradesBySchoolId(long schoolId) {
        return gradeMapper.getGradesBySchoolId(schoolId);
    }

    @Override
    public List<Grade> getGradeByName(String name) {
        return gradeMapper.getGradeByName(name);
    }

    @Override
    public long insertGrade(Grade grade) {
        gradeMapper.insertGrade(grade);
        return grade.getId();
    }

    @Override
    public void updateGrade(Grade grade) {
        gradeMapper.updateGrade(grade);
    }

    @Override
    public void deleteGrade(long id) {
        gradeMapper.deleteGrade(id);
    }
}
