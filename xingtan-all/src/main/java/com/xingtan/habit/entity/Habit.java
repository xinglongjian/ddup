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
    private int status;

    /**
     * 适合性别，0 所有，1 男，2女
     */
    private int sexScope;
    /**
     * 年龄范围 2.0 - 5.0
     */
    private int ageStart;
    /**
     * 年龄范围 2.0 - 5.0
     */
    private int ageEnd;
}
