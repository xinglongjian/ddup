package com.xingtan.account.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xingtan.common.entity.BaseEntity;
import com.xingtan.common.entity.IDCardType;
import com.xingtan.common.entity.UserSexEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
     * 英文名称
     */
    private String enName;

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

    /**
     * Byte64 头像
     */
    private String headImage;
    /**
     * 证件类型
     */
    private IDCardType idCardType;
    /**
     * 证件号
     */
    private String idCardNo;
    /**
     * 省/直辖市
     */
    private String country;
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
     * 个人介绍
     */
    private String introduce;

    /**
     * 注册来源
     */
    private String fromSource;
    /**
     * 微信号
     */
    private String weixin;
    /**
     * qq号
     */
    private String qq;
    /**
     * 由谁创建
     */
    private long createdBy;

    /**
     * 是否可用
     */
    private boolean enabled = Boolean.TRUE;
}
