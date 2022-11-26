package com.dhu.service;

import com.dhu.dao.AdminDao;
import com.dhu.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;

public interface AdminService {

//	管理员功能：
//            	管理员登录界面
//    a)	管理员登录
    Admin login(String adminId, String adminPwd);
//	管理员主页
//    b)	进入用户主页，进行用户操作
//    c)	查看消息（举报，请求（建立圈子，修改圈子信息，任命新圈主））
//    d)	对用户进行封号操作
//    e)	处理信息发送消息
}
