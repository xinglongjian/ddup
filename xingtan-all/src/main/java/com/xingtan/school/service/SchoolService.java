package com.xingtan.school.service;

import com.xingtan.school.entity.School;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchoolService {
    School getSchoolById(long id);
    List<School> getSchoolsByIds(List<Long> ids);
    List<School> getSchoolByName(String name);
    long insertSchool(School school);
    void updateSchool(School school);
    void deleteSchool(long id);

}
