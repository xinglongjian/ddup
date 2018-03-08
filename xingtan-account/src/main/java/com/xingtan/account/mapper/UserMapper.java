package com.xingtan.account.mapper;

import com.xingtan.account.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
@Mapper
public interface UserMapper {
    /**
     * 获取所有用户
     * @return
     */
    List<User> getAll();

    /**
     * 通过ID获取
     * @param id
     * @return
     */
    User getUserById(@Param("id") Long id);

    /**
     * 通过用户名获取
     * @param userName
     * @return
     */
    User getUserByUserName(@Param("userName") String userName);

    /**
     * 通过telephone获取
     * @param telephone
     * @return
     */
    User getUserByPhone(@Param("telephone") String telephone);

    /**
     * 通过email获取
     * @param email
     * @return
     */
    User getUserByEmail(@Param("email") String email);

    /**
     * 插入
     * @param user
     */
    void insertUser(User user);

    /**
     * 修改
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除
     * @param id
     */
    void deleteUser(@Param("id") Long id);
}
