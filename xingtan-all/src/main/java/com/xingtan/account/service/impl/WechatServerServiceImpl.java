package com.xingtan.account.service.impl;

import com.alibaba.fastjson.JSON;
import com.xingtan.account.bean.Jscode2sessionResult;
import com.xingtan.account.bean.Token;
import com.xingtan.account.service.WechatServerService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WechatServerServiceImpl implements WechatServerService {

    @Value("#{wechat.appId}")
    private String appId;
    @Value("#{wechat.appSecret}")
    private String appSecret;

    @Value("#{wechat.jscode2sessionUrl}")
    private String jscode2sessionUrl;

    @Value("#{wechat.getTokenUrl}")
    private String getTokenUrl;

    private final static String GRANT_TYPE_SESSION = "authorization_code";
    private final static String GRANT_TYPE_TOKEN = "client_credential";

    @Override
    public Jscode2sessionResult getJscode2session(String code) {
        String url = String.format(jscode2sessionUrl, appId, appSecret, code, GRANT_TYPE_SESSION);
        log.info("getJscode2session Start");
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().readTimeout(10, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(url).build();
        try{
            Response response = client.newCall(request).execute();
            log.info("getJscode2session Success!body:{}", response.body().string());
            return JSON.parseObject(response.body().string(), Jscode2sessionResult.class);
        }catch (Exception ex) {
            log.error("getJscode2session Fail, error:{}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Token getTokenByOpenid(String openId) {
        String url = String.format(getTokenUrl, appId, appSecret);
        log.info("getTokenByOpenid Start");
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().readTimeout(10, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(url).build();
        try{
            Response response = client.newCall(request).execute();
            log.info("getTokenByOpenid Success!body:{}", response.body().string());
            return JSON.parseObject(response.body().string(), Token.class);
        }catch (Exception ex) {
            log.error("getTokenByOpenid Fail, error:{}", ex.getMessage());
        }
        return null;
    }
}
