package com.xingtan.common.entity;

/**
 * 班级状态
 */
public enum ClassesStatus {

    /**
     * 未开始报名
     */
    NOT_START,
    /**
     * 报名进行中
     */
    START,
    /**
     * 已满额（结束时间未到，但已满额）
     */
    FULL,
    /**
     * 已结束（结束时间已到，不一定满额）
     */
    END
}
