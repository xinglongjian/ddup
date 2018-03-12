package com.xingtan.choose.service;

import com.xingtan.choose.entity.CourseType;

import java.util.List;

public interface CourseTypeService {
    CourseType getCourseTypeById(long id);
    CourseType getCourseTypeByName(String name);
    List<CourseType> getCourseTypeByOrganId(long organId);
    long insertCourseType(CourseType courseType);
    void updateCourseType(CourseType courseType);
    void deleteCourseType(long id);
}
