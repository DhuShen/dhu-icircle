package com.dhu.service.impl;

import com.dhu.dao.CircleDao;
import com.dhu.dao.RequestDao;
import com.dhu.domain.Request;
import com.dhu.service.RequestService;
import com.dhu.service.staticService.MyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    RequestDao requestDao;

    @Override//建立圈子
    public boolean setRequest0(String circleName, String circleContent, String setId)//建立圈子请求
    {
          Request request=new Request();
          request.setRequestCircleName(circleName);
          request.setRequestCircleContent(circleContent);
          request.setRequestType(0);
          request.setRequestTime(MyTime.getNowTime());
          request.setRequest_UserId(setId);
          return requestDao.insertRequest(request) > 1;
    }

    @Override//任命新圈主（未判断是否是圈主，在circle服务有）
    public boolean setRequest1(long circleId, String circleUserId, String setId) {
        Request request=new Request();
        request.setRequestCircleId(circleId);
        request.setRequestType(0);
        request.setRequestTime(MyTime.getNowTime());
        request.setRequest_UserId(setId);
        return requestDao.insertRequest(request) > 1;
    }
}
