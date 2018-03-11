package com.xingtan.account.mapper;

import com.xingtan.account.entity.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 */
@Mapper
public interface GroupMapper {
    /**
     * 通过ID获取
     *
     * @param id
     * @return
     */
    Group getGroupById(@Param("id") Long id);

    /**
     * 通过用户名获取
     *
     * @param userName
     * @return
     */
    Group getGroupByUserName(@Param("userName") String userName);

    /**
     * 通过telephone获取
     *
     * @param telephone
     * @return
     */
    Group getGroupByPhone(@Param("telephone") String telephone);

    /**
     * 通过email获取
     *
     * @param email
     * @return
     */
    Group getGroupByEmail(@Param("email") String email);

    /**
     * 通过idCardNo获取
     *
     * @param idCardNo
     * @return
     */
    Group getGroupByIdCardNo(@Param("idCardNo") String idCardNo);

    /**
     * 插入
     *
     * @param group
     */
    void insertGroup(Group group);

    /**
     * 修改
     *
     * @param group
     */
    void updateGroup(Group group);

    /**
     * 删除
     *
     * @param id
     */
    void deleteGroup(@Param("id") Long id);
}
