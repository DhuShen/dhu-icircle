package com.dhu.service.impl;

import com.dhu.dao.CircleDao;
import com.dhu.domain.Circle;
import com.dhu.domain.view.CircleView;
import com.dhu.service.CircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CircleServiceImpl implements CircleService {
    @Autowired
    CircleDao circleDao;

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


}
