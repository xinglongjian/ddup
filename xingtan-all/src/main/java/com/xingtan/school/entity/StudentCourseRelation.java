package com.xingtan.school.entity;

import com.xingtan.common.entity.BaseEntity;
import com.xingtan.common.entity.StudentDuty;
import lombok.*;

/**
 * @Author zhengweiliang
 * @Date 2018/4/17 8:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class StudentCourseRelation extends BaseEntity {

    /**
     * 学生
     */
    private long studentId;
    /**
     * 课程ID
     */
    private long CourseId;
    /**
     * 学生职务
     */
    private StudentDuty duty = StudentDuty.NONE;
}
