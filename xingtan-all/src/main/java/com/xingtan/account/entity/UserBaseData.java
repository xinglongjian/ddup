package com.xingtan.account.entity;

import com.xingtan.common.entity.BaseEntity;
import com.xingtan.common.entity.IDCardType;
import com.xingtan.common.entity.UserSexEnum;
import lombok.*;

import java.util.Date;

/**
 * @Author zhengweiliang
 * @Date 2018/4/19 8:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class UserBaseData extends BaseEntity{

    /**
     * 用户ID
     */
    private long userId;
    /**
     * 性别
     */
    private UserSexEnum sex;
    /**
     * 生日
     */
    private Date birthday;
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
     * 国家
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
     * 个人介绍
     */
    private String introduce;
    /**
     * 微信号
     */
    private String weixin;
    /**
     * qq号
     */
    private String qq;
    /**
     *
     */
    private String openId;
    /**
     *
     */
    private String unionId;
}
