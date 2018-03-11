package com.xingtan.account.service.impl;

import com.xingtan.account.entity.Student;
import com.xingtan.account.mapper.StudentMapper;
import com.xingtan.account.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student getStudentById(long id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentMapper.getStudentByEmail(email);
    }

    @Override
    public Student getStudentByUserName(String userName) {
        return studentMapper.getStudentByUserName(userName);
    }

    @Override
    public Student getStudentByPhone(String telephone) {
        return studentMapper.getStudentByPhone(telephone);
    }

    @Override
    public Student getStudentByIdCardNo(String idCardNo) {
        return studentMapper.getStudentByIdCardNo(idCardNo);
    }

    @Override
    public long insertStudent(Student student) {
        studentMapper.insertStudent(student);
        return student.getId();
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    public void deleteStudent(long id) {
        studentMapper.deleteStudent(id);
    }
}
