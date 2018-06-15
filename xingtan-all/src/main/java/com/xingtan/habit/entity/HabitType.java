package com.xingtan.habit.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.*;

/**
 * 习惯分类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class HabitType extends BaseEntity {
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否可用
     */
    private int status;
}
