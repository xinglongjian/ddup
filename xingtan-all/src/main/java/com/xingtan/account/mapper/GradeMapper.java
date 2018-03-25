package com.xingtan.account.mapper;

import com.xingtan.account.entity.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
@Mapper
public interface GradeMapper {
    /**
     * 通过ID获取
     *
     * @param id
     * @return
     */
    Grade getGradeById(@Param("id") long id);

    /**
     *
     * @param schoolId
     * @return
     */
    List<Grade> getGradesBySchoolId(@Param("schoolId") long schoolId);

    /**
     * 通过名称模糊获取
     *
     * @param name
     * @return
     */
    List<Grade> getGradeByName(@Param("name") String name);


    /**
     * 插入
     *
     * @param grade
     */
    void insertGrade(Grade grade);

    /**
     * 修改
     *
     * @param grade
     */
    void updateGrade(Grade grade);

    /**
     * 删除
     *
     * @param id
     */
    void deleteGrade(@Param("id") long id);
}
