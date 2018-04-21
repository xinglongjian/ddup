package com.xingtan.account.service;

import com.xingtan.account.bean.Jscode2sessionResult;
import com.xingtan.account.bean.Token;

/**
 * 调用Weixin平台的服务
 */
public interface WechatServerService {

    /**
     * 获取Session
     * @param code
     * @return
     */
    Jscode2sessionResult getJscode2session(String code);

    /**
     * 获取Token
     * @param openId
     * @return
     */
    Token getTokenByOpenid(String openId);
}
