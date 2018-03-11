package com.xingtan.account.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.*;

/**
 * 教师机构关系表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class TeacherOrganRelation extends BaseEntity {
    /**
     * 教师ID
     */
    private long teacherId;

    /**
     * 机构ID
     */
    private long organId;
}
