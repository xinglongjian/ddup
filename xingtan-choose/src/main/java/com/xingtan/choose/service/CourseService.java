package com.xingtan.choose.service;

import com.xingtan.choose.entity.Course;

import java.util.List;

public interface CourseService {
    Course getCourseById(long id);
    List<Course> getCourseByOrganId(long organId);
    long insertCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(long id);
}
