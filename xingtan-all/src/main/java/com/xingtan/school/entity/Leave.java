package com.xingtan.school.entity;

import com.xingtan.common.entity.BaseEntity;
import com.xingtan.common.entity.Notification;

import java.util.Date;

/**
 * 请假
 *
 * @Author zhengweiliang
 * @Date 2018/4/25 13:01
 */
public class Leave extends Notification {
    /**
     * 学生ID
     */
    private Long studentId;
    /**
     * 班级ID
     */
    private Long gradeId;
    /**
     *
     */
    private Date startTime;
    /**
     *
     */
    private Date endTime;
    /**
     * 是否已读
     */
    private int isRead;

    /**
     * 已读时间
     */
    private Date readTime;
}
