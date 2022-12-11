package com.dhu.service;

import com.dhu.domain.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {
    //根据id查姓名
    String  getUserNameById(String userId);
    //    a)	注册【在user中】
    boolean userRegister(String userId, String userPwd);
    boolean userRegister(String userId, String userPwd,String userImg);
    //    b)	登录【在user中】
    User userLogin(String userId, String userPwd);

    //   i1)查看其他用户中心或自己的信息【在user中】
    User userCheck(String userId);

    //   i2)修改个人信息-基本信息【在user中】
    boolean updateUserInfo(User user);

    //   i2)修改个人信息-密码【在user中】
    boolean updateUserPwd(String userId,String oldPwd,String newPwd);

    //    d)	对用户进行封号操作【在user中】
    boolean closeUser(String userId);

    //根据用户Id获取Img
    String getImgUserById(String userId);
}
