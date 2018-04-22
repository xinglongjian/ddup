package com.xingtan.account.service;

import com.xingtan.account.entity.UserBaseData;
import org.apache.ibatis.annotations.Param;

public interface UserBaseDataService {

    UserBaseData getDataByUserId(long userId);

    UserBaseData getDataByOpenId(String openId);

    void insertUserBaseData(UserBaseData baseData);

    void updateUserBaseData(UserBaseData baseData);

    void deleteUserBaseData(long userId);
}
