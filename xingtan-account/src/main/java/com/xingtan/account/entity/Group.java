package com.xingtan.account.entity;

import lombok.*;

/**
 * 集团
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Group extends BaseAccount {
    /**
     * 企业/组织名称
     */
    private String organName;

    /**
     * 企业营业执照注册号/组织机构代码
     */
    private String organCode;

    /**
     * 创建年份
     */
    private int buildYear;

    /**
     * 客服电话
     */
    private String customerTelephone;
}
