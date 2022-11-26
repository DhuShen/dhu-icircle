package com.dhu.service;

import com.dhu.domain.User;
import org.springframework.transaction.annotation.Transactional;
//	用户功能
//	用户登录注册界面
//    a)	注册【在user中】
//    b)	登录【在user中】
//    c)	查找圈子、贴子【在Circle,Post中】
//    d)	浏览圈子、帖子、评论【在Circle,Post,Discuss中】
//    e)	举报用户（帖子下、评论下）【在Report中】
//    f)	在帖子下发布评论【在Discuss中】
//    g)	点赞帖子、评论【在post，discuss中】
//    h)	用户查看消息（消息）【在message中】
/*
      i)	进入用户个人中心（我的或别人的）！
            (i1)查看其他用户中心或自己的【在user中】
            (i2)修改个人信息【在user中】
            (i3)查看自己管理的圈子
            (i4)查看自己加入的圈子
            (i5)查看自己的帖子
            (i6)查看消息
*/
//    j)	加入圈子【在circle中】
//    k)	建立圈子（需要向管理员申请）【在request、circle中】
//            	圈子内
//    l)	退出圈子【在circle中】
//    m)	在圈子中发布帖子【在post中】
//    n)	在圈子中查看圈内成员信息【在circle中】
//	圈主在用户功能上增加以下功能
//    o)	解散圈子【在circle中】
//    p)	任命新圈主（需要向管理员申请）【在request、circle中】
//    q)	修改圈子相关信息（需要向管理员申请）【在request、circle中】
//    r)	添加圈子的精华帖【在post中】
//    s)	取消圈子的精华帖【在post中】
//    t)	撤销圈内成员的帖子/评论【在post，discuss中】
//    u)	踢掉圈内成员【在circle中】//圈主可用：退出圈子
//    d)	对用户进行封号操作【在user中】


@Transactional
public interface UserService {
    //    a)	注册【在user中】
    boolean userRegister(String userId, String userPwd);

    //    b)	登录【在user中】
    User userLogin(String userId, String userPwd);

    //            (i1)查看其他用户中心或自己的【在user中】
    User userCheck(String userId);
    //            (i2)修改个人信息【在user中】
    boolean updateUserInfo(String userId,String password,String userImg);

    //    d)	对用户进行封号操作【在user中】
    boolean closeUser(String userId);
}
