package com.xingtan.account.bean;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Token implements Serializable {
    private String access_token;
    private int expires_in;
    //错误时
    private String errcode;
    private String errmsg;
}
