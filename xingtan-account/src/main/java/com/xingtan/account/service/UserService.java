package com.xingtan.account.service;

import com.xingtan.account.entity.User;

public interface UserService {
    User getUserById(Long id);
    User getUserByEmail(String email);
    User getUserByUserName(String userName);
    User getUserByPhone(String telephone);
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}
