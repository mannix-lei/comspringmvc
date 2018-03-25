package com.service.impl;

import com.dao.UserDaoInf;
import com.service.serviceInf.UserServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Tellyes_worker on 2017/12/4/0004.
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements UserServiceInf {

    private UserDaoInf userDao;
    @Autowired
    public void setUserDao(UserDaoInf userDao){
        this.userDao = userDao;
    }
    @Override
    public User getUserById(String id) {
        return this.userDao.findUserById(id);
    }
    @Override
    public List<User> findAllUser() {
        return this.userDao.findAllUser();
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public String login(String name){
        return userDao.findUserByName(name);
    }

    @Override
    public boolean register(User user){
        userDao.addUser(user);
        return true;
    }

}
