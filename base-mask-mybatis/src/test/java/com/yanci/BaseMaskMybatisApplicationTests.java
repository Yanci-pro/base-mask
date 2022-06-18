package com.yanci;

import com.yanci.bean.User;
import com.yanci.dao.UserDao;
import com.yanci.util.ResourcesUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class BaseMaskMybatisApplicationTests {
    @Resource
    UserDao userDao;

    @Test
    void contextLoads() {
        String key = ResourcesUtil.getProperties("key");
        System.out.println("key="+key);
        System.out.println("userDao=" + userDao);
        User user = userDao.queryUser("1");
        System.out.println(user);

        List<User> users = userDao.queryUserList();
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

}
