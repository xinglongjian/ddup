package com.xingtan.account.service.impl;

import com.xingtan.account.entity.User;
import com.xingtan.account.mapper.UserMapper;
import com.xingtan.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(long id) {
        return userMapper.getUserById(id);
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
