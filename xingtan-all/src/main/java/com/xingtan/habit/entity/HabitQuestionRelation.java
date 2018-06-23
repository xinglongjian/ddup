package com.xingtan.habit.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.*;

/**
 * 习惯和问题 关联表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class HabitQuestionRelation extends BaseEntity {

    private long habitId;

    private long questionId;
}
