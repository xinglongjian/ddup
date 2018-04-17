package com.xingtan.school.entity;

import com.xingtan.common.entity.BaseEntity;
import com.xingtan.common.entity.StudentDuty;

/**
 * @Author zhengweiliang
 * @Date 2018/4/17 8:48
 */
public class StudentCourseRelation extends BaseEntity {

    private long studentId;

    private long CourseId;

    private StudentDuty duty = StudentDuty.NONE;
}
