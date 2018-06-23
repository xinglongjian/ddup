package com.xingtan.habit.entity;

import com.xingtan.common.entity.BaseEntity;

public class HabitQuestionItem extends BaseEntity {
    /**
     * 问题ID
     */
    private long questionId;
    /**
     * 显示顺序
     */
    private int seq;
    /**
     * 序号
     */
    private String seqNo;
    /**
     * 内容
     */
    private String content;

    /**
     * 影响
     */
    private String affect;

    /**
     * 分数
     */
    private int score;
}
