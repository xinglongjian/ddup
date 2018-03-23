package com.xingtan.habit.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.*;

/**
 * 学生和习惯的关系
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class UserHabitRelation extends BaseEntity {

    /**
     * 用户ID
     */
    private long userId;

    /**
     * 习惯ID
     */
    private long habitId;

    /**
     * 谁添加的
     */
    private long createdUserId;
}
