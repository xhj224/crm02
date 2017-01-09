package com.tz.user.service.impl;

import com.tz.entity.User;
import com.tz.user.dao.IUserDao;
import com.tz.user.service.IUserService;
import com.tz.util.BeanFactory;

public class UserServiceImpl implements IUserService {

	IUserDao userDao = (IUserDao) BeanFactory.getBean("userDao");

	@Override
	public User login(String username, String password) {
		return userDao.selectUser(username, password);
	}
}
