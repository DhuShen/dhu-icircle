package com.dhu.service.impl;

import com.dhu.dao.CircleDao;
import com.dhu.domain.Circle;
import com.dhu.domain.User;
import com.dhu.domain.view.CircleView;
import com.dhu.service.CircleService;
import com.dhu.tools.MyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CircleServiceImpl implements CircleService {
    @Autowired
    CircleDao circleDao;

    //根据circleId查圈子
    @Override
    public Circle selectById(int circleId) {
        return circleDao.selectByCircleId(circleId);
    }

    //判断是不是圈主
    @Override
    public boolean isCircleMaster(String userId, int circleId) {
        Circle circle = circleDao.selectByCircleId(circleId);
        return Objects.equals(userId, circle.getCircle_UserId());
    }

    //判断是否在圈子中(圈主也属于圈内成员）
    @Override
    public boolean isInCircle(String userId, int circleId) {
        return circleDao.selectConnection(userId, circleId) != null;
    }

    //    c)	查找圈子、贴子【在Circle,Post中】--查询圈子，模糊查询
    public List<Circle> searchCircle(String circleName) {
        return circleDao.selectByName(circleName);
    }

    //    d)	浏览圈子、帖子、评论【在Circle,Post,Discuss中】--获取热度圈子
    @Override
    public List<CircleView> getHotCircle() {
        return circleDao.selectTen();
    }

    //   i3)查看自己管理的圈子【在circle中】
    @Override
    public List<Circle> getMyCircle(String userId) {
        return circleDao.selectById(userId);
    }

    //   i4)查看自己加入的圈子【在circle中】
    @Override
    public List<Circle> getInCircle(String userId) {
        return circleDao.selectConnectionById(userId);
    }

    //    j)	加入圈子【在circle中】--事先不在圈子里
    @Override
    public boolean enterCircle(String userId, int circleId) {
        if (!isInCircle(userId, circleId)) {
            return circleDao.insertConnection(userId, circleId) > 1;
        } else
            return false;
    }

    //    n)	在圈子中查看圈内成员信息【在circle中】
    @Override
    public List<User> selectUserInCircle(int circleId) {
        return circleDao.selectUserInCircle(circleId);
    }

    //    u)	踢掉圈内成员【在circle中】--在圈子内（退出圈子）, 圈主用（踢人）
    @Override
    public boolean quitCircle(String userId, int circleId) {
        return circleDao.deleteConnection(userId, circleId) > 1;
    }

    //    o)	解散圈子【在circle中】--AOP
    @Override
    public boolean dissolveCircle(String userId, int circleId) {
        Circle circle = circleDao.selectByCircleId(circleId);
        if (Objects.equals(circle.getCircle_UserId(), userId)) {
            return circleDao.deleteCircle(circleId) > 0;
        } else
            return false;
    }

    //    k)	建立圈子（需要向管理员申请）【在request、circle中】--管理员用
    @Override
    public boolean setUpCircle(String circleName, String circleImg, String circleContent, String userId) {
        //先处理内容中的换行和空格
        circleContent=circleContent.replace("&nbsp"," ");
        circleContent=circleContent.replace("<br>","\n");
        Circle circle = new Circle();
        circle.setCircleName(circleName);
        circle.setCircleImg(circleImg);
        circle.setCircleContent(circleContent);
        circle.setCircleLife(0);//判断圈子是否被封
        circle.setCircleTime(MyTime.getNowTime());
        circle.setCircle_UserId(userId);
        return circleDao.insertCircle(circle) > 0;
    }

    //    p)	任命新圈主（需要向管理员申请）【在request、circle中】--管理员用
    @Override
    public boolean setNewCircleUser(int circleId, String userId) {
        Circle circle = new Circle();
        circle.setCircleId(circleId);
        circle.setCircle_UserId(userId);
        return circleDao.updateCircleMaster(circle) > 0;
    }

    //    q)	修改圈子相关信息（需要向管理员申请）【在request、circle中】--管理员用
    @Override
    public boolean updateCircleContent(int circleId, String circleName, String circleImg, String circleContent) {
        //先处理内容中的换行和空格
        circleContent=circleContent.replace("&nbsp"," ");
        circleContent=circleContent.replace("<br>","\n");
        Circle circle = new Circle();
        circle.setCircleId(circleId);
        circle.setCircleName(circleName);
        circle.setCircleImg(circleImg);
        circle.setCircleContent(circleContent);
        return circleDao.updateCircleContent(circle) > 0;
    }
}
