package com.asura.demo.service;

import com.asura.demo.dao.UserDao;
import com.asura.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public boolean userIsExix(String userName){
        if(1==userDao.userIsExix(userName))
            return true;
        else
            return false;
    }

    public boolean insertUser(User user){
        if(1==userDao.insertUser(user))
            return true;
        else
            return false;
    }

    public User selectUserByUsername(String userName){
        return userDao.selectUserByUsername(userName);
    }

}
