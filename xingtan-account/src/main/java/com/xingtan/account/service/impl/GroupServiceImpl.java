package com.xingtan.account.service.impl;

import com.xingtan.account.entity.Group;
import com.xingtan.account.mapper.GroupMapper;
import com.xingtan.account.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public Group getGroupById(long id) {
        return groupMapper.getGroupById(id);
    }

    @Override
    public Group getGroupByEmail(String email) {
        return groupMapper.getGroupByEmail(email);
    }

    @Override
    public Group getGroupByUserName(String userName) {
        return groupMapper.getGroupByUserName(userName);
    }

    @Override
    public Group getGroupByPhone(String telephone) {
        return groupMapper.getGroupByPhone(telephone);
    }

    @Override
    public Group getGroupByIdCardNo(String idCardNo) {
        return groupMapper.getGroupByIdCardNo(idCardNo);
    }

    @Override
    public long insertGroup(Group group) {
        groupMapper.insertGroup(group);
        return group.getId();
    }

    @Override
    public void updateGroup(Group group) {
        groupMapper.updateGroup(group);
    }

    @Override
    public void deleteGroup(long id) {
        groupMapper.deleteGroup(id);
    }
}
