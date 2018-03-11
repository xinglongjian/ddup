package com.xingtan.course.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.*;

/**
 * 课程类型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class CourseType extends BaseEntity{

    /**
     * 类型
     */
    private String name;

    /**
     * 机构ID
     */
    private long organId;
}
