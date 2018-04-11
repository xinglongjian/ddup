package com.xingtan.customer.entity;

import com.xingtan.common.entity.BaseEntity;
import com.xingtan.common.entity.QuestionStatus;
import lombok.*;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Question extends BaseEntity {

    /**
     * 内容
     */
    private String content;

    /**
     * 类型ID
     */
    private long typeId;

    /**
     * 创建用户ID
     */
    private long createdUserId;

    /**
     * 问题状态
     */
    private QuestionStatus status = QuestionStatus.NEW;

}
