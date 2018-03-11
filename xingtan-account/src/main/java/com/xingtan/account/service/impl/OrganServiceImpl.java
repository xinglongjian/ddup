package com.xingtan.account.service.impl;

import com.xingtan.account.entity.Organ;
import com.xingtan.account.mapper.OrganMapper;
import com.xingtan.account.service.OrganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganServiceImpl implements OrganService {

    @Autowired
    private OrganMapper organMapper;

    @Override
    public Organ getOrganById(long id) {
        return organMapper.getOrganById(id);
    }

    @Override
    public Organ getOrganByEmail(String email) {
        return organMapper.getOrganByEmail(email);
    }

    @Override
    public Organ getOrganByUserName(String userName) {
        return organMapper.getOrganByUserName(userName);
    }

    @Override
    public Organ getOrganByPhone(String telephone) {
        return organMapper.getOrganByPhone(telephone);
    }

    @Override
    public Organ getOrganByIdCardNo(String idCardNo) {
        return organMapper.getOrganByIdCardNo(idCardNo);
    }

    @Override
    public long insertOrgan(Organ organ) {
        organMapper.insertOrgan(organ);
        return organ.getId();
    }

    @Override
    public void updateOrgan(Organ organ) {
        organMapper.updateOrgan(organ);
    }

    @Override
    public void deleteOrgan(long id) {
        organMapper.deleteOrgan(id);
    }
}
