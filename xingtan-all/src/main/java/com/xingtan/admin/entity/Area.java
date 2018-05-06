package com.xingtan.admin.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xingtan.common.entity.BaseEntity;
import lombok.*;

/**
 * 区域
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Area extends BaseEntity{
    /**
     * 编码
     */
    private String no;
    /**
     * 名称
     */
    private String name;
    /**
     * 父编码
     */
    private String parentNo;
    /**
     * 区码 010
     */
    private String areaCode;
    /**
     * 等级
     */
    private int areaLevel;
    /**
     * 类型名称，京，市，区
     */
    private String typeName;
    /**
     * 简称
     */
    private String abbreviate;
    /**
     * 邮编
     */
    private String postCode;
    /**
     * 拼音
     */
    private String pinyin;
    /**
     * 拼音简称
     */
    private String pinyinBrief;
}
