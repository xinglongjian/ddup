package com.xingtan.account.service;

import com.xingtan.account.bean.WeixinUser;
import com.xingtan.account.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    User getUserById(long id);
    List<User> getUsersByIds(List<Long> ids);
    User getUserByEmail(String email);
    User getUserByUserName(String userName);
    User getUserByPhone(String telephone);
    User getUserByToken(String token);
    User getUserByIdCardNo(String idCardNo);
    User saveByWxUser(WeixinUser user);
    User saveByParent(String nickName,String realName,String enName,String createdUserId);
    long insertUser(User student);
    void updateUser(User student);
    void deleteUser(long id);

}
