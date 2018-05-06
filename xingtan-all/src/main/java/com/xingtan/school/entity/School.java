package com.xingtan.school.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xingtan.common.entity.BaseEntity;
import com.xingtan.common.entity.SchoolType;
import lombok.*;

/**
 * 学校
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class School extends BaseEntity{

    /**
     * 学校名称
     */
    private String name;

    /**
     * 学校英文名称
     */
    private String enName;

    /**
     * 学校代码
     */
    private String code;

    /**
     * 创建年份
     */
    private int buildYear;

    /**
     * Byte64 头像
     */
    private String headImage;

    /**
     * 客服电话
     */
    private String telephone;

    /**
     * 父ID，对于有集团和分校时有用
     */
    private long parentId;

    /**
     * 创建人
     */
    private long createdUserId;

    /**
     * 省/直辖市
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 其他地址
     */
    private String address;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 学校介绍
     */
    private String introduce;

    /**
     * 类型
     */
    private SchoolType type = SchoolType.SCHOOL;

    /**
     * 是否可用
     */
    private boolean enabled = Boolean.TRUE;

}
