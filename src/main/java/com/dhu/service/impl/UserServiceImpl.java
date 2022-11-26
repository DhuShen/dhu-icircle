package com.dhu.service.impl;

import com.dhu.dao.UserDao;
import com.dhu.domain.User;
import com.dhu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

//    	用户功能
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public boolean userRegister(String userId, String userPwd) {
        if (userDao.userSelectById(userId) == null) {//注册用户不存在（未注册）
            User user = new User(userId, userPwd);
            userDao.userInsert(user);
            return true;
        } else
            return false;
    }

    @Override
    public User userLogin(String userId, String userPwd) {
        User user = userDao.userSelectById(userId);
        if ((user != null) &&//登录用户存在（已注册）
                (user.getUserLife() != 1) &&//登录用户未封号
                (Objects.equals(user.getUserPassword(), userPwd)))//登录用户密码正确
        {
            user.setUserPassword("");//不返回密码
            return user;
        } else
            return null;
    }
}
