package com.tz.service;

import com.tz.entity.User;

public interface IUserService {

	/**
	 * 登陆
	 */
	User login(String username, String password);
}
