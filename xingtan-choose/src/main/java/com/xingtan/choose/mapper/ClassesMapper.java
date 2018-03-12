package com.xingtan.choose.mapper;

import com.xingtan.choose.entity.Classes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ClassesMapper {
    Classes getClassesById(@Param("id") long id);
    void insertClasses(Classes classes);
    void updateClasses(Classes classes);
    void deleteClasses(@Param("id") long id);
}
