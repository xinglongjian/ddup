package com.xingtan.account.entity;

import com.xingtan.common.entity.BaseEntity;
import com.xingtan.common.entity.FamilyRelation;
import lombok.*;

/**
 * 家庭成员关系
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class StudentParentRelation extends BaseEntity {

    /**
     * 学生
     */
    private long studentId;

    /**
     * 家长
     */
    private long parentId;

    /**
     * 关系
     */
    private FamilyRelation relation = FamilyRelation.MOTHER;

    /**
     * 别名
     */
    private String alias;

    public StudentParentRelation(long studentId, long parentId) {
        this.studentId = studentId;
        this.parentId = parentId;
    }

    public StudentParentRelation(long studentId, long parentId, FamilyRelation relation) {
        this.studentId = studentId;
        this.parentId = parentId;
        this.relation = relation;
    }
}
