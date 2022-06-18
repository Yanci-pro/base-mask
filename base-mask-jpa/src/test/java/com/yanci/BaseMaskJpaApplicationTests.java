package com.yanci;

import com.yanci.bean.User;
import com.yanci.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class BaseMaskJpaApplicationTests {
    @Resource
    UserDao userDao;

    @Test
    void contextLoads() {

        List<User> all = userDao.findAll();
        for (User user : all) {
            System.out.println(user);
        }
        Optional<User> byId = userDao.findById("1");
        System.out.println(byId.get());
    }

}
