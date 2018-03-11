package com.xingtan.account.service;

import com.xingtan.account.entity.Student;

public interface StudentService {
    Student getStudentById(long id);
    Student getStudentByEmail(String email);
    Student getStudentByUserName(String userName);
    Student getStudentByPhone(String telephone);
    Student getStudentByIdCardNo(String idCardNo);
    long insertStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(long id);
}
