package com.xingtan.choose.mapper;

import com.xingtan.choose.entity.CourseType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseTypeMapper {
    CourseType getCourseTypeById(@Param("id") long id);
    CourseType getCourseTypeByName(@Param("name") String name);
    List<CourseType> getCourseTypeByOrganId(@Param("organId") long organId);
    void insertCourseType(CourseType courseType);
    void updateCourseType(CourseType courseType);
    void deleteCourseType(@Param("id") long id);
}
