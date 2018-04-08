package com.xingtan.account.entity;

import com.xingtan.common.entity.BaseEntity;
import com.xingtan.common.entity.TeacherType;
import lombok.*;

/**
 * 教师和班级关系
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class TeacherGradeRelation extends BaseEntity {

    /**
     * 教师ID
     */
    private long teacherId;

    /**
     * 年级
     */
    private long gradeId;

    /**
     * 类型
     */
    private TeacherType type = TeacherType.MAIM;

    /**
     * 别名
     */
    private String alias;
}
