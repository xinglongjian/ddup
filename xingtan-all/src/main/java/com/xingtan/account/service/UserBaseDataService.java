package com.xingtan.account.service;

import com.xingtan.account.entity.UserBaseData;

public interface UserBaseDataService {

    UserBaseData getDataByUserId(long userId);

    void insertUserBaseData(UserBaseData baseData);

    void updateUserBaseData(UserBaseData baseData);

    void deleteUserBaseData(long userId);
}
