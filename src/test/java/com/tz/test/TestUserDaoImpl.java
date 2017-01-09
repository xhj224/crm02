package com.tz.test;

import com.tz.AppConfig;
import com.tz.entity.User;
import com.tz.user.dao.IUserDao;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.annotation.Resource;

@ContextConfiguration(classes = AppConfig.class)
public class TestUserDaoImpl extends AbstractTestNGSpringContextTests {
    @Resource
    private IUserDao userDao;

    @Test
    public void TestSelectUser() {
        User user = userDao.selectUser("张三", "123123");
        System.out.println(user);
    }
}
