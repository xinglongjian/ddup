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
public class StudentHabitRelation extends BaseEntity {

    /**
     * 学生ID
     */
    private long studentId;

    /**
     * 习惯ID
     */
    private long habitId;
}
