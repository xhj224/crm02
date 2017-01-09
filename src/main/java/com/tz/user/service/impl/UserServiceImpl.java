package com.tz.user.service.impl;

import com.tz.entity.User;
import com.tz.user.dao.IUserDao;
import com.tz.user.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    IUserDao userDao;

    @Override
    public User login(String username, String password) {
        return userDao.selectUser(username, password);
    }
}
