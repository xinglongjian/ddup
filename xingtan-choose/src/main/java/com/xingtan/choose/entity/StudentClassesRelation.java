package com.xingtan.choose.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.*;

/**
 * 学生与班级关系表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class StudentClassesRelation extends BaseEntity{

    /**
     * 学生ID
     */
    private long studentId;

    /**
     * 班级ID
     */
    private long classesId;

}
