package com.dhu.service.impl;

import com.dhu.dao.UserDao;
import com.dhu.domain.User;
import com.dhu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public String getUserNameById(String userId) {
        return userDao.getUserNameById(userId);
    }

    //    a)	注册【在user中】
    @Override
    public boolean userRegister(String userId, String userPwd) {
        if (userDao.userSelectById(userId) == null) {//注册用户不存在（未注册）
            User user = new User(userId, userPwd);
            userDao.userInsert(user);
            return true;//用户不存在返回true
        } else
            return false;//用户已存在返回false
    }

    //    b)	登录【在user中】
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

    //   i1)查看其他用户中心或自己的【在user中】
    @Override
    public User userCheck(String userId) {
        User user = userDao.userSelectById(userId);
        user.setUserPassword("");//不返回密码
        return user;
    }

    //   i2)修改个人信息-基本信息【在user中】-传String userId, String userImg, String userName,
    //                           String userSex, String userMajor, String userIntroduction
    @Override
    public boolean updateUserInfo(User user) {
        return userDao.updateUserInfo(user) > 0;
    }

    //   i2)修改个人信息-密码【在user中】
    @Override
    public boolean updateUserPwd(String userId, String oldPwd, String newPwd) {
        User user = userDao.userSelectById(userId);
        if (!Objects.equals(user.getUserPassword(), oldPwd)) return false;//旧密码输入不正确
        else {
            User user1 = new User();
            user1.setUserId(userId);
            user1.setUserPassword(newPwd);
            return userDao.updateUserPwd(user1) > 0;
        }
    }

    //    d)	对用户进行封号操作【在user中】
    @Override
    public boolean closeUser(String userId) {
        return userDao.closeUser(userId) > 0;
    }
}
