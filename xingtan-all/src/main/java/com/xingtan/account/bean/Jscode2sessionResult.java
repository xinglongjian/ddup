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
    private String session_key;
    private String unionid;
    //错误时
    private String errcode;
    private String errmsg;

}
