package com.xingtan.account.service;

import com.xingtan.account.entity.Teacher;

public interface TeacherService {
    Teacher getTeacherById(long id);
    Teacher getTeacherByEmail(String email);
    Teacher getTeacherByUserName(String userName);
    Teacher getTeacherByPhone(String telephone);
    Teacher getTeacherByIdCardNo(String idCardNo);
    long insertTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacher(long id);
}
