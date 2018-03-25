package com.xingtan.account.entity;

import com.xingtan.common.entity.BaseEntity;
import com.xingtan.common.entity.GradeStatus;
import lombok.*;

/**
 * 班级
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Grade extends BaseEntity {

    /**
     * 学校ID
     */
    private long schoolId;

    /**
     * 年级名称
     */
    private String name;

    /**
     * 哪一级
     */
    private int year;

    /**
     * 班级创建者
     */
    private long createdUserId;

    /**
     * 班级状态
     */
    private GradeStatus status = GradeStatus.INITIALIZATION;

}
