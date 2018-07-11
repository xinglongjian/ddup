package com.xingtan.habit.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.*;

/**
 * 孩子习惯的记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class UserHabitRecord extends BaseEntity {
    /**
     * 用户ID（）
     */
    private long userId;
    /**
     * 习惯ID
     */
    private long habitId;

    /**
     * 习惯问题ID
     */
    private long habitQuestionId;

    /**
     * 选中的选项ID
     */
    private long checkedItemId;

    /**
     * 谁给打的分
     */
    private long createdUserId;

}
