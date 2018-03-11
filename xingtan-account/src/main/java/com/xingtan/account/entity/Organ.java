package com.xingtan.account.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Organ extends BaseAccount {

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


}
