package com.xingtan.choose.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.*;

/**
 * 课程
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Course extends BaseEntity {
    /**
     * 课程名字
     */
    private String name;

    /**
     * 课程编码
     */
    private String code;

    /**
     * 课程类型
     */
    private long courseTypeId;

    /**
     * 课程介绍
     */
    private String introduce;

    /**
     * 机构ID
     */
    private String organId;
}
