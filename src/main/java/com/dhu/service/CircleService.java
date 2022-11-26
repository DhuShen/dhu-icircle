package com.dhu.service;

import com.dhu.domain.Circle;
import com.dhu.domain.User;
import com.dhu.domain.view.CircleView;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CircleService {
    //判断是否为圈主
    boolean isCircleMaster(String userId, long circleId);

    //判断是否在圈子中(圈主也属于圈内成员）
    boolean isInCircle(String userId, long circleId);

    //    c)	查找圈子、贴子【在Circle,Post中】
    List<Circle> searchCircle(String circleName);//查询圈子

    //    d)	浏览圈子、帖子、评论【在Circle,Post,Discuss中】
    List<CircleView> getHotCircle();//获取热门圈子排序

    List<Circle> getMyCircle(String userId);//获取我的圈子

    //    j)	加入圈子【在circle中】
    boolean enterCircle(String userId, long circleId);

    //    k)	建立圈子（需要向管理员申请）【在request、circle中】
    boolean setUpCircle(String circleName, String circleImg, String circleContent, String userId);//建立圈子

    //    n)	在圈子中查看圈内成员信息【在circle中】
    List<User> selectUserInCircle(long circleId);

    //    l)	退出圈子【在circle中】
    //    u)	踢掉圈内成员【在circle中】
    boolean quitCircle(String userId, long circleId);

    //    o)	解散圈子【在circle中】
    boolean dissolveCircle(String userId, long circleId);

    //    p)	任命新圈主（需要向管理员申请）【在request、circle中】
    boolean setNewCircleUser(long circleId, String userId);

    //    q)	修改圈子相关信息（需要向管理员申请）【在request、circle中】
    boolean updateCircleContent(long circleId, String circleName, String circleImg, String circleContent);
}
