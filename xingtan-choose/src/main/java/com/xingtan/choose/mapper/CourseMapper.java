package com.xingtan.choose.mapper;

import com.xingtan.choose.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {

    Course getCourseById(@Param("id") long id);
    List<Course> getCourseByOrganId(@Param("organId") long organId);
    void insertCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(@Param("id") long id);
}
