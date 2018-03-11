package com.xingtan.account.mapper;

import com.xingtan.account.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 */
@Mapper
public interface TeacherMapper {
    /**
     * 通过ID获取
     *
     * @param id
     * @return
     */
    Teacher getTeacherById(@Param("id") Long id);

    /**
     * 通过用户名获取
     *
     * @param userName
     * @return
     */
    Teacher getTeacherByUserName(@Param("userName") String userName);

    /**
     * 通过telephone获取
     *
     * @param telephone
     * @return
     */
    Teacher getTeacherByPhone(@Param("telephone") String telephone);

    /**
     * 通过email获取
     *
     * @param email
     * @return
     */
    Teacher getTeacherByEmail(@Param("email") String email);

    /**
     * 通过idCardNo获取
     *
     * @param idCardNo
     * @return
     */
    Teacher getTeacherByIdCardNo(@Param("idCardNo") String idCardNo);

    /**
     * 插入
     *
     * @param teacher
     */
    void insertTeacher(Teacher teacher);

    /**
     * 修改
     *
     * @param teacher
     */
    void updateTeacher(Teacher teacher);

    /**
     * 删除
     *
     * @param id
     */
    void deleteTeacher(@Param("id") Long id);
}
