package com.xingtan.account.entity;

import com.xingtan.common.entity.AdminType;
import com.xingtan.common.entity.BaseEntity;
import lombok.*;

/**
 * 教师学校关系表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class TeacherSchoolRelation extends BaseEntity {
    /**
     * 教师ID
     */
    private long teacherId;

    /**
     * 学校ID
     */
    private long schoolId;

    /**
     * 管理类型
     */
    private AdminType type = AdminType.OTHER;

    /**
     * 别名
     */
    private String alias;

}
