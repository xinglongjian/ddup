package com.xingtan.choose.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.*;

/**
 * 教师与班级关系
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class TeacherClassesRelation extends BaseEntity {
    /**
     * 教师ID
     */
    private long teacherId;

    /**
     * 班级ID
     */
    private long classesId;
}
