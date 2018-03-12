package com.xingtan.account.mapper;

import com.xingtan.account.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 */
@Mapper
public interface StudentMapper {
    /**
     * 通过ID获取
     *
     * @param id
     * @return
     */
    Student getStudentById(@Param("id") long id);

    /**
     * 通过用户名获取
     *
     * @param userName
     * @return
     */
    Student getStudentByUserName(@Param("userName") String userName);

    /**
     * 通过telephone获取
     *
     * @param telephone
     * @return
     */
    Student getStudentByPhone(@Param("telephone") String telephone);

    /**
     * 通过email获取
     *
     * @param email
     * @return
     */
    Student getStudentByEmail(@Param("email") String email);

    /**
     * 通过idCardNo获取
     *
     * @param idCardNo
     * @return
     */
    Student getStudentByIdCardNo(@Param("idCardNo") String idCardNo);

    /**
     * 插入
     *
     * @param student
     * @return new Id
     */
    long insertStudent(Student student);

    /**
     * 修改
     *
     * @param student
     */
    void updateStudent(Student student);

    /**
     * 删除
     *
     * @param id
     */
    void deleteStudent(@Param("id") long id);
}
