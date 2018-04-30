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
     * 通过ID获取
     *
     * @param id
     * @return
     */
    User getUserById(@Param("id") long id);

    /**
     * 批量获取
     *
     * @param ids
     * @return
     */
    List<User> getUsersByIds(@Param("ids") List<Long> ids);

    /**
     * 通过用户名获取
     *
     * @param userName
     * @return
     */
    User getUserByUserName(@Param("userName") String userName);

    /**
     * 通过telephone获取
     *
     * @param telephone
     * @return
     */
    User getUserByPhone(@Param("telephone") String telephone);

    /**
     * 通过email获取
     *
     * @param email
     * @return
     */
    User getUserByEmail(@Param("email") String email);

    /**
     * 通过idCardNo获取
     *
     * @param idCardNo
     * @return
     */
    User getUserByIdCardNo(@Param("idCardNo") String idCardNo);

    /**
     * 用户总数
     *
     * @return
     */
    long getCounts();

    /**
     * 插入
     *
     * @param student
     * @return new Id
     */
    void insertUser(User student);

    /**
     * 修改
     *
     * @param student
     */
    void updateUser(User student);

    /**
     * 删除
     *
     * @param id
     */
    void deleteUser(@Param("id") long id);
}
