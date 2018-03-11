package com.xingtan.account.service.impl;

import com.xingtan.account.entity.Teacher;
import com.xingtan.account.mapper.TeacherMapper;
import com.xingtan.account.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher getTeacherById(long id) {
        return teacherMapper.getTeacherById(id);
    }

    @Override
    public Teacher getTeacherByEmail(String email) {
        return teacherMapper.getTeacherByEmail(email);
    }

    @Override
    public Teacher getTeacherByUserName(String userName) {
        return teacherMapper.getTeacherByUserName(userName);
    }

    @Override
    public Teacher getTeacherByPhone(String telephone) {
        return teacherMapper.getTeacherByPhone(telephone);
    }

    @Override
    public Teacher getTeacherByIdCardNo(String idCardNo) {
        return teacherMapper.getTeacherByIdCardNo(idCardNo);
    }

    @Override
    public long insertTeacher(Teacher teacher) {
        teacherMapper.insertTeacher(teacher);
        return teacher.getId();
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherMapper.updateTeacher(teacher);
    }

    @Override
    public void deleteTeacher(long id) {
        teacherMapper.deleteTeacher(id);
    }
}
