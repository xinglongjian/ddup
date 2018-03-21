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
public class StudentHabitRecord extends BaseEntity {
    /**
     * 学生ID
     */
    private long studentId;
    /**
     * 习惯ID
     */
    private long habitId;
    /**
     * 分数（-2 -1 0 1 2）
     */
    private int score;
    /**
     * 谁给打的分
     */
    private long createdUserId;
}
