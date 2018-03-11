package com.xingtan.account.service;

import com.xingtan.account.entity.Organ;

public interface OrganService {
    Organ getOrganById(long id);
    Organ getOrganByEmail(String email);
    Organ getOrganByUserName(String userName);
    Organ getOrganByPhone(String telephone);
    Organ getOrganByIdCardNo(String idCardNo);
    long insertOrgan(Organ organ);
    void updateOrgan(Organ organ);
    void deleteOrgan(long id);
}
