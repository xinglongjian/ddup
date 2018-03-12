package com.xingtan.choose.service.impl;

import com.xingtan.choose.entity.Classes;
import com.xingtan.choose.mapper.ClassesMapper;
import com.xingtan.choose.service.ClassesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesMapper classesMapper;

    @Override
    public Classes getClassesById(long id) {
        return classesMapper.getClassesById(id);
    }

    @Override
    public long insertClasses(Classes classes) {
        classesMapper.insertClasses(classes);
        return classes.getId();
    }

    @Override
    public void updateClasses(Classes classes) {
        classesMapper.updateClasses(classes);
    }

    @Override
    public void deleteClasses(long id) {
        classesMapper.deleteClasses(id);
    }
}
