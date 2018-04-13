package com.xingtan.school.mapper;

import com.xingtan.school.entity.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
@Mapper
public interface SchoolMapper {
    /**
     * 通过ID获取
     *
     * @param id
     * @return
     */
    School getSchoolById(@Param("id") long id);

    /**
     * 通过名称模糊获取
     *
     * @param name
     * @return
     */
    List<School> getSchoolByName(@Param("name") String name);


    /**
     * 插入
     *
     * @param school
     */
    void insertSchool(School school);

    /**
     * 修改
     *
     * @param school
     */
    void updateSchool(School school);

    /**
     * 删除
     *
     * @param id
     */
    void deleteSchool(@Param("id") long id);
}
