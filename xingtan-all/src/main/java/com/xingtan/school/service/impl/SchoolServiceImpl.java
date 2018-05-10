package com.xingtan.school.service.impl;

import com.xingtan.school.entity.School;
import com.xingtan.school.mapper.SchoolMapper;
import com.xingtan.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolMapper schoolMapper;


    @Override
    public School getSchoolById(long id) {
        return schoolMapper.getSchoolById(id);
    }

    @Override
    public List<School> getSchoolsByIds(List<Long> ids) {
        return schoolMapper.getSchoolsByIds(ids);
    }

    @Override
    public List<School> getSchoolByName(String name) {
        return schoolMapper.getSchoolByName(name);
    }

    @Override
    public long insertSchool(School school) {
        schoolMapper.insertSchool(school);
        return school.getId();
    }

    @Override
    public void updateSchool(School school) {
        schoolMapper.updateSchool(school);
    }

    @Override
    public void deleteSchool(long id) {
        schoolMapper.deleteSchool(id);
    }
}
