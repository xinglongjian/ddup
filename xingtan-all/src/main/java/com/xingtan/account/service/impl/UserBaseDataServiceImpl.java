package com.xingtan.account.service.impl;

import com.xingtan.account.entity.UserBaseData;
import com.xingtan.account.mapper.UserBaseDataMapper;
import com.xingtan.account.service.UserBaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBaseDataServiceImpl implements UserBaseDataService {
    @Autowired
    private UserBaseDataMapper userBaseDataMapper;

    @Override
    public UserBaseData getDataByUserId(long userId) {
        return userBaseDataMapper.getDataByUserId(userId);
    }

    @Override
    public UserBaseData getDataByOpenId(String openId) {
        return userBaseDataMapper.getDataByOpenId(openId);
    }

    @Override
    public void insertUserBaseData(UserBaseData baseData) {
        userBaseDataMapper.insertUserBaseData(baseData);
    }

    @Override
    public void updateUserBaseData(UserBaseData baseData) {
        userBaseDataMapper.updateUserBaseData(baseData);
    }

    @Override
    public void deleteUserBaseData(long userId) {
        userBaseDataMapper.deleteUserBaseData(userId);
    }
}
