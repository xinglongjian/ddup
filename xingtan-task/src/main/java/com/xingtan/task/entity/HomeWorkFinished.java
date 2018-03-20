package com.xingtan.task.entity;

import com.xingtan.common.entity.BaseEntity;

/**
 * 作业完成
 */
public class HomeWorkFinished extends BaseEntity {
    /**
     * 哪个作业
     */
    private long homeWorkId;
    /**
     * 谁完成的
     */
    private long finishedUserId;
    /**
     * 谁提交的（比如家长）
     */
    private long submitUserId;

    

}
