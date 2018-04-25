package com.xingtan.common.entity;

import com.xingtan.common.entity.BaseEntity;

/**
 * 班级通知
 */
public class Notification extends BaseEntity{
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 创建者
     */
    private long createdUserId;

}
