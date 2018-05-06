package com.xingtan.school.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.*;

import java.util.Date;
import java.util.prefs.BackingStoreException;

/**
 * Created by xinglongjian on 5/6 2018 22:33.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class StudentSchoolRelation extends BaseEntity {
    /**
     * 学生ID
     */
    private long studentId;

    /**
     * 学校ID
     */
    private long schoolId;
    /**
     * 入学时间
     */
    private Date startDate;
    /**
     * 毕业时间
     */
    private Date endDate;
}
