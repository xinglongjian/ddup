package com.xingtan.task.entity;

import com.xingtan.common.entity.BaseEntity;

import java.util.Date;

/**
 * 家庭作业
 */
public class HomeWork extends BaseEntity{
    /**
     * 谁留的
     */
    private long createByUserId;

    /**
     * 课程ID
     */
    private long courseId;

    /**
     * 作业内容
     */
    private String content;

    /**
     * 是否需要交作业
     */
    private boolean isNeedSubmit;
    /**
     * 最后一天
     */
    private Date lastDay;
    /**
     * 提交方式：图片（I）视频（VI）语音（VO）文本(T)
     */
    private String submitType;

}
