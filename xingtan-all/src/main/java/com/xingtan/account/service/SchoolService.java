package com.xingtan.account.service;

import com.xingtan.account.entity.School;

import java.util.List;

public interface SchoolService {
    School getSchoolById(long id);
    List<School> getSchoolByName(String name);
    long insertSchool(School school);
    void updateSchool(School school);
    void deleteSchool(long id);

}
