package com.xingtan.common.entity;

/**
 * 班级状态
 */
public enum GradeStatus {

    /**
     * 未开始报名
     */
    INITIALIZATION,
    /**
     * 报名进行中
     */
    ENROLL_START,
    /**
     * 已满额（结束时间未到，但已满额）
     */
    ENROLL_FULL,
    /**
     * 已结束（结束时间已到，不一定满额）
     */
    ENROLL_END,

    /**
     * 课程进行中
     */
    GRADE_START,

    /**
     * 结束
     */
    GRADE_END

}
