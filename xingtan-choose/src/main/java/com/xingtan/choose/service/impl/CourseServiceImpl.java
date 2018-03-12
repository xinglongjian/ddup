package com.xingtan.choose.service.impl;

import com.xingtan.choose.entity.Course;
import com.xingtan.choose.mapper.CourseMapper;
import com.xingtan.choose.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Course getCourseById(long id) {
        return courseMapper.getCourseById(id);
    }

    @Override
    public List<Course> getCourseByOrganId(long organId) {
        return courseMapper.getCourseByOrganId(organId);
    }

    @Override
    public long insertCourse(Course course) {
        courseMapper.insertCourse(course);
        return course.getId();
    }

    @Override
    public void updateCourse(Course course) {
        courseMapper.updateCourse(course);
    }

    @Override
    public void deleteCourse(long id) {
        courseMapper.deleteCourse(id);
    }
}
