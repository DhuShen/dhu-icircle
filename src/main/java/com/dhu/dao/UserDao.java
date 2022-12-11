package com.dhu.dao;

import com.dhu.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    //id查名字
    String getUserNameById(@Param("userId") String userId);
    //查询返回对应用户信息
    User userSelectById(@Param("userId") String userId);

    //添加用户
    boolean userInsert(User user);

    //封号用户（设置userLife等于1）
    int closeUser(@Param("userId") String userId);

    //修改用户一般信息
    int updateUserInfo(User user);

    //修改用户密码
    int updateUserPwd(User user);

    //根据用户Id获取Img
    String selectImgById(@Param("userId")String userId);
}
