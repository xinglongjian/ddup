package com.xingtan.habit.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.*;

/**
 * 习惯
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Habit extends BaseEntity {
    /**
     * 习惯名称
     */
    private String name;
    /**
     * 习惯代码
     */
    private String code;
    /**
     * 分类ID
     */
    private long habitTypeId;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否可用
     */
    private int enabled;
}
