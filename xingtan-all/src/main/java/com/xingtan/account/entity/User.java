package com.xingtan.account.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xingtan.common.entity.BaseEntity;
import com.xingtan.common.entity.IDCardType;
import com.xingtan.common.entity.UserSexEnum;
import com.xingtan.common.entity.UserStatus;
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
     * 昵称
     */
    private String nickName;

    /**
     * 英文名称
     */
    private String enName;
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
     * 注册来源
     */
    private String fromSource;

    /**
     * 由谁创建
     */
    private long createdUserId;

    /**
     * 是否可用
     */
    private int status = UserStatus.ENABLE.ordinal();
}
