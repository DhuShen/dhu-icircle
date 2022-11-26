package com.dhu.service.impl;

import com.dhu.dao.CircleDao;
import com.dhu.domain.Circle;
import com.dhu.domain.User;
import com.dhu.domain.view.CircleView;
import com.dhu.service.CircleService;
import com.dhu.service.staticService.MyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CircleServiceImpl implements CircleService {
    @Autowired
    CircleDao circleDao;

    //判断是不是圈主
    @Override
    public boolean isCircleMaster(String userId, long circleId) {
        Circle circle = circleDao.selectByCircleId(circleId);
        return Objects.equals(userId, circle.getCircle_UserId());
    }

    //判断是否在圈子中(圈主也属于圈内成员）
    @Override
    public boolean isInCircle(String userId, long circleId) {
        return circleDao.selectConnection(userId, circleId) != null;
    }

    //查找圈子
    @Override
    public List<Circle> searchCircle(String circleName) {
        return circleDao.selectByName(circleName);
    }

    //获取热度圈子
    @Override
    public List<CircleView> getHotCircle() {
        return circleDao.selectTen();
    }

    //获取我管理的圈子
    @Override
    public List<Circle> getMyCircle(String userId) {
        return circleDao.selectById(userId);
    }

    //不在圈子内（加入圈子）
    @Override
    public boolean enterCircle(String userId, long circleId) {
        return circleDao.insertConnection(userId, circleId) > 1;
    }

    //查询圈内成员信息
    @Override
    public List<User> selectUserInCircle(long circleId) {
        return circleDao.selectUserInCircle(circleId);
    }

    //在圈子内（退出圈子）
    // 圈主用（踢人）
    @Override
    public boolean quitCircle(String userId, long circleId) {
        return circleDao.deleteConnection(userId, circleId) > 1;
    }

    //圈主用（解散圈子）
    @Override
    public boolean dissolveCircle(String userId, long circleId) {
        Circle circle = circleDao.selectByCircleId(circleId);
        if (Objects.equals(circle.getCircle_UserId(), userId)) {
            return circleDao.deleteCircle(circleId) > 0;
        } else
            return false;
    }

    //管理员用（建立圈子）
    @Override
    public boolean setUpCircle(String circleName, String circleImg, String circleContent, String userId) {
        Circle circle = new Circle();
        circle.setCircleName(circleName);
        circle.setCircleImg(circleImg);
        circle.setCircleContent(circleContent);
        circle.setCircleLife(0);//判断圈子是否被封
        circle.setCircleTime(MyTime.getNowTime());
        circle.setCircle_UserId(userId);
        return circleDao.insertCircle(circle) > 0;
    }

    //管理员用(任命新圈主）
    @Override
    public boolean setNewCircleUser(long circleId, String userId) {
        Circle circle = new Circle();
        circle.setCircleId(circleId);
        circle.setCircle_UserId(userId);
        return circleDao.updateCircleMaster(circle) > 0;
    }

    //管理员用（修改圈子简介）
    @Override
    public boolean updateCircleContent(long circleId, String circleName, String circleImg, String circleContent) {
        Circle circle = new Circle();
        circle.setCircleId(circleId);
        circle.setCircleName(circleName);
        circle.setCircleImg(circleImg);
        circle.setCircleContent(circleContent);
        return circleDao.updateCircleContent(circle) > 0;
    }
}
