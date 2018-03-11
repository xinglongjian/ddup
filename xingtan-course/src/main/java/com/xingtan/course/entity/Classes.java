package com.xingtan.course.entity;

import com.xingtan.common.entity.BaseEntity;
import com.xingtan.common.entity.ClassesStatus;
import lombok.*;

import java.util.Date;

/**
 * 班级
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Classes extends BaseEntity {
    /**
     * 班级名称
     */
    private String name;

    /**
     * 班级编码
     */
    private String code;

    /**
     * 班级介绍
     */
    private String introduce;

    /**
     * 班级状态
     */
    private ClassesStatus status = ClassesStatus.NOT_START;

    /**
     * 学年
     */
    private int year;

    /**
     * 学生总数
     */
    private int studentCount;

    /**
     * 课次
     */
    private int lessonCount;

    /**
     * 课时(分钟)
     */
    private int lessonPeriodMin;

    /**
     * 学期
     */
    private String term;

    /**
     * 开课日期
     */
    private Date openDate;

    /**
     * 上课时间(二,四:17:00-18:00)形式
     */
    private String classesTime;

    /**
     * 教室地址
     */
    private String classroom;

    /**
     * 课程ID
     */
    private long courseId;

    /**
     * 费用
     */
    private double cost;
}
