package com.xingtan.account.bean;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Jscode2sessionResult implements Serializable {
    private  String openid;
    private String sessionKey;
    private String unionid;
    // 本系统的用户ID
    private Long userId;
    //错误时
    private String errcode;
    private String errmsg;

}
