package com.xingtan.school.entity;

import com.xingtan.common.entity.BaseEntity;
import com.xingtan.common.entity.TeacherType;

/**
 * @Author zhengweiliang
 * @Date 2018/4/17 8:46
 */
public class TeacherCourseRelation extends BaseEntity {
    private long teacherId;

    private long courseId;

    private TeacherType teacherType = TeacherType.MAIM;
}
