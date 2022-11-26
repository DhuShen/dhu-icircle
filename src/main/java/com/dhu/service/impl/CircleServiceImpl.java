package com.dhu.service.impl;

import com.dhu.dao.CircleDao;
import com.dhu.domain.Circle;
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

    @Override
    public boolean isCircleMaster(String userId, long circleId) {
        Circle circle=circleDao.selectByCircleId(circleId);
        return Objects.equals(userId, circle.getCircle_UserId());
    }

    @Override
    public List<Circle> searchCircle(String circleName) {
        return circleDao.selectByName(circleName);
    }

    @Override
    public List<CircleView> getHotCircle() {
        return circleDao.selectTen();
    }

    @Override
    public List<Circle> getMyCircle(String userId) {
        return circleDao.selectById(userId);
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

    @Override
    public boolean dissolveCircle(String userId, long circleId) {
        Circle circle = circleDao.selectByCircleId(circleId);
        if (Objects.equals(circle.getCircle_UserId(), userId)) {
            return circleDao.deleteCircle(circleId) > 0;
        } else
            return false;
    }

    //管理员用
    @Override
    public boolean setNewCircleUser(long circleId, String userId) {
        Circle circle=new Circle();
        circle.setCircleId(circleId);
        circle.setCircle_UserId(userId);
        return circleDao.updateCircle(circle)>0;
    }
}
