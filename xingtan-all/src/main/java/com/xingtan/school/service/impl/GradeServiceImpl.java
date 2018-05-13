package com.xingtan.school.service.impl;

import com.xingtan.school.bean.GradeData;
import com.xingtan.school.bean.GradeItemData;
import com.xingtan.school.entity.Grade;
import com.xingtan.school.entity.School;
import com.xingtan.school.mapper.GradeMapper;
import com.xingtan.school.mapper.SchoolMapper;
import com.xingtan.school.mapper.StudentGradeRelationMapper;
import com.xingtan.school.mapper.TeacherGradeRelationMapper;
import com.xingtan.school.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private StudentGradeRelationMapper studentGradeRelationMapper;
    @Autowired
    private TeacherGradeRelationMapper teacherGradeRelationMapper;

    @Override
    public Grade getGradeById(long id) {
        return gradeMapper.getGradeById(id);
    }

    @Override
    public GradeData getGradeDataById(long id) {
        Grade grade = gradeMapper.getGradeById(id);
        GradeData data = new GradeData();
        if (grade != null) {
            data.fromGrade(grade);
            School school = schoolMapper.getSchoolById(grade.getSchoolId());
            data.setSchoolName(school == null ? "" : school.getName());
        }
        return data;
    }

    @Override
    public GradeItemData getGradeItemData(long id) {
        Grade grade = gradeMapper.getGradeById(id);
        GradeItemData data = new GradeItemData();
        if (grade != null) {
            data.fromGrade(grade);
            School school = schoolMapper.getSchoolById(grade.getSchoolId());
            data.setSchoolName(school == null ? "" : school.getName());
        }
        int studentCount = studentGradeRelationMapper.getCountOfStudent(id);
        int teacherCount = teacherGradeRelationMapper.getCountOfTeacher(id);
        data.setStudentNum(studentCount);
        data.setTeacherNum(teacherCount);
        return data;
    }

    @Override
    public List<Grade> getGradesByIds(List<Long> ids) {
        return gradeMapper.getGradesByIds(ids);
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
