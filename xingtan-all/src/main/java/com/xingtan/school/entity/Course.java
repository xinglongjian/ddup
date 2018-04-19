package com.xingtan.school.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.*;

/**
 * 课程
 * @Author zhengweiliang
 * @Date 2018/4/17 8:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Course extends BaseEntity {
    /**
     * 班级
     */
    private long gradeId;
    /**
     * 名称
     */
    private String name;
    /**
     * 类别
     */
    private int classifyId;
    /**
     * 课时
     */
    private int courseCount;
    /**
     * 上课时间
     */
    private String haveTime;


}
