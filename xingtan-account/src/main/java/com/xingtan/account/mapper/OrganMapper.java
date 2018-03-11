package com.xingtan.account.mapper;

import com.xingtan.account.entity.Organ;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 */
@Mapper
public interface OrganMapper {
    /**
     * 通过ID获取
     *
     * @param id
     * @return
     */
    Organ getOrganById(@Param("id") Long id);

    /**
     * 通过用户名获取
     *
     * @param userName
     * @return
     */
    Organ getOrganByUserName(@Param("userName") String userName);

    /**
     * 通过telephone获取
     *
     * @param telephone
     * @return
     */
    Organ getOrganByPhone(@Param("telephone") String telephone);

    /**
     * 通过email获取
     *
     * @param email
     * @return
     */
    Organ getOrganByEmail(@Param("email") String email);

    /**
     * 通过idCardNo获取
     *
     * @param idCardNo
     * @return
     */
    Organ getOrganByIdCardNo(@Param("idCardNo") String idCardNo);

    /**
     * 插入
     *
     * @param organ
     */
    void insertOrgan(Organ organ);

    /**
     * 修改
     *
     * @param organ
     */
    void updateOrgan(Organ organ);

    /**
     * 删除
     *
     * @param id
     */
    void deleteOrgan(@Param("id") Long id);
}
