package com.dhu.service;

import com.dhu.domain.Admin;
import org.springframework.transaction.annotation.Transactional;
//	管理员功能：
//            	管理员登录界面
//    a)	管理员登录
//            	管理员主页
//    b)	进入用户主页，进行用户操作（暂无）
//    c)	查看消息（举报，请求（建立圈子，修改圈子信息，任命新圈主））【在report，request中】
//    d)	对用户进行封号操作【在user中】
//    e)	处理信息发送消息【在message中】
@Transactional
public interface AdminService {
    //    a)	管理员登录--登录
    Admin login(String adminId, String adminPwd);

    //    a)	管理员登录--注册
    boolean register(String adminId, String adminPwd);
}
