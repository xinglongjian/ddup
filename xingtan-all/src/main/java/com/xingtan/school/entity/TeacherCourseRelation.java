package com.xingtan.school.entity;

import com.xingtan.common.entity.BaseEntity;
import com.xingtan.common.entity.TeacherType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author zhengweiliang
 * @Date 2018/4/17 8:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherCourseRelation extends BaseEntity {
    /**
     * 教师ID
     */
    private long teacherId;
    /**
     * 课程ID
     */
    private long courseId;
    /**
     * 教师的类型
     */
    private TeacherType teacherType = TeacherType.MAIM;
}
