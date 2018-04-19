package com.xingtan.account.mapper;

import com.xingtan.account.entity.User;
import com.xingtan.account.entity.UserBaseData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
@Mapper
public interface UserBaseDataMapper {
    /**
     * 通过ID获取
     *
     * @param id
     * @return
     */
    UserBaseData getById(@Param("id") long id);
    /**
     * 插入
     *
     * @param user
     * @return new Id
     */
    long insertUserBaseData(UserBaseData user);

    /**
     * 修改
     *
     * @param student
     */
    void updateUserBaseData(UserBaseData user);

    /**
     * 删除
     *
     * @param id
     */
    void deleteUserBaseData(@Param("id") long id);
}
