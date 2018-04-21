package com.xingtan.account.service.impl;

import com.xingtan.account.bean.WeixinUser;
import com.xingtan.account.entity.User;
import com.xingtan.account.entity.UserBaseData;
import com.xingtan.account.mapper.UserBaseDataMapper;
import com.xingtan.account.mapper.UserMapper;
import com.xingtan.account.service.UserService;
import com.xingtan.common.entity.FromSource;
import com.xingtan.common.entity.UserStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserBaseDataMapper userBaseDataMapper;

    @Override
    public User getUserById(long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> getUsersByIds(List<Long> ids) {
        return userMapper.getUsersByIds(ids);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    @Override
    public User getUserByPhone(String telephone) {
        return userMapper.getUserByPhone(telephone);
    }

    @Override
    public User getUserByIdCardNo(String idCardNo) {
        return userMapper.getUserByIdCardNo(idCardNo);
    }

    @Override
    public User saveByWxUser(WeixinUser wxUser){
        try{
            User user = new User();
            user.setUserName(wxUser.getNickName());
            user.setNickName(wxUser.getNickName());
            user.setRealName(wxUser.getNickName());
            user.setCreatedUserId(1L);
            user.setFromSource(FromSource.WEIXIN.name());
            user.setStatus(UserStatus.ENABLE.ordinal());
            userMapper.insertUser(user);

            UserBaseData baseData = new UserBaseData();
            baseData.setUserId(user.getId());
            baseData.setCountry(wxUser.getCountry());
            baseData.setProvince(wxUser.getProvince());
            baseData.setCity(wxUser.getCity());
            baseData.setHeadImage(wxUser.getAvatarUrl());
            baseData.setOpenId(wxUser.getOpenId());
            baseData.setUnionId(wxUser.getUnionId());
            userBaseDataMapper.insertUserBaseData(baseData);
            log.info("saveByWxUser Success.");
            return user;
        }catch (Exception ex) {
            log.error("saveByWxUser error, wxUser:{}, causeBy:{}", wxUser, ex.getMessage());
            return null;
        }
    }

    @Override
    public long insertUser(User user) {
        userMapper.insertUser(user);
        return user.getId();
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(long id) {
        userMapper.deleteUser(id);
    }
}
