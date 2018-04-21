package com.xingtan.account.bean;

import io.swagger.annotations.ApiImplicitParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author zhengweiliang
 * @Date 2018/4/19 13:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WeixinUser implements Serializable{
    /**
     *
     */
    private String openId;
    /**
     *
     */
    private String unionId;

    /**
     * 昵称
     */
    private String nickName;
    /**
     * 性别
     */
    private int gender;
    /**
     * 国家
     */
    private String country;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 头像
     */
    private String avatarUrl;
}
