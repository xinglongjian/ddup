package com.xingtan.account.service;

import com.xingtan.account.entity.Group;

public interface GroupService {
    Group getGroupById(long id);
    Group getGroupByEmail(String email);
    Group getGroupByUserName(String userName);
    Group getGroupByPhone(String telephone);
    Group getGroupByIdCardNo(String idCardNo);
    long insertGroup(Group group);
    void updateGroup(Group group);
    void deleteGroup(long id);
}
