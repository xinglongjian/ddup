package com.xingtan.account.entity;

import lombok.*;

import java.util.Date;

/**
 * 登录用户
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
public class User extends BaseEntity {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 真实名称
     */
    private String realName;

    /**
     * 性别
     */
    private UserSexEnum sex;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 手机号
     */
    private String telephone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
}
