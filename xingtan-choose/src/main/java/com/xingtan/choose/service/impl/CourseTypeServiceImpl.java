package com.xingtan.choose.service.impl;

import com.xingtan.choose.entity.CourseType;
import com.xingtan.choose.mapper.CourseTypeMapper;
import com.xingtan.choose.service.CourseTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseTypeServiceImpl implements CourseTypeService {

    @Autowired
    private CourseTypeMapper courseTypeMapper;


    @Override
    public CourseType getCourseTypeById(long id) {
        return courseTypeMapper.getCourseTypeById(id);
    }

    @Override
    public CourseType getCourseTypeByName(String name) {
        return courseTypeMapper.getCourseTypeByName(name);
    }

    @Override
    public List<CourseType> getCourseTypeByOrganId(long organId) {
        return courseTypeMapper.getCourseTypeByOrganId(organId);
    }

    @Override
    public long insertCourseType(CourseType courseType) {
        courseTypeMapper.insertCourseType(courseType);
        return courseType.getId();
    }

    @Override
    public void updateCourseType(CourseType courseType) {
        courseTypeMapper.updateCourseType(courseType);
    }

    @Override
    public void deleteCourseType(long id) {
        courseTypeMapper.deleteCourseType(id);
    }
}
