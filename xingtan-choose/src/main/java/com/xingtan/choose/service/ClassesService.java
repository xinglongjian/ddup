package com.xingtan.choose.service;

import com.xingtan.choose.entity.Classes;


public interface ClassesService {
    Classes getClassesById(long id);
    long insertClasses(Classes classes);
    void updateClasses(Classes classes);
    void deleteClasses(long id);
}
