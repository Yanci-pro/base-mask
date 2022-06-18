package com.yanci.dao;

import com.yanci.bean.User;

import java.util.List;

/**
 * @author Yanci
 * @date 2022/6/18  10:05
 * Description
 */
public interface UserDao {
    /**
     * 查询苏哦有用户列表
     *
     * @return
     */
    List<User> queryUserList();

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    User queryUser(String id);

}
