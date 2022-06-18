package com.yanci.dao;


import com.yanci.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yanci
 * @date 2022/6/18  10:05
 * Description
 */

public interface UserDao extends JpaRepository<User, String> {
}

