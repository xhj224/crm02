package com.tz.user.dao.impl;

import com.tz.entity.User;
import com.tz.user.dao.IUserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class UserDaoImpl implements IUserDao {
    @Resource
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public User selectUser(final String username, final String password) {

        String hql = "from User where username=:username and password=:password";
        return (User) getSession().createQuery(hql).setString("username", username).setString("password", password).uniqueResult();

    }

}
