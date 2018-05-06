package com.xingtan.school.service.impl;

import com.xingtan.school.mapper.StudentSchoolRelationMapper;
import com.xingtan.school.service.StudentSchoolRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xinglongjian on 5/6 2018 22:40.
 */
@Service
public class StudentSchoolRelationServiceImpl implements StudentSchoolRelationService {

    @Autowired
    private StudentSchoolRelationMapper studentSchoolRelationMapper;
}
