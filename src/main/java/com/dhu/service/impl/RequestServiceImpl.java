package com.dhu.service.impl;

import com.dhu.dao.CircleDao;
import com.dhu.dao.RequestDao;
import com.dhu.domain.Request;
import com.dhu.service.RequestService;
import com.dhu.tools.MyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    RequestDao requestDao;
    CircleDao circleDao;

    //    k)	建立圈子（需要向管理员申请）【在request、circle中】
    @Override
    public boolean setRequest0(String circleName, String circleContent, String setId)//建立圈子请求
    {
        //先处理内容中的换行和空格
        circleContent=circleContent.replace("&nbsp"," ");
        circleContent=circleContent.replace("<br>","\n");
        Request request = new Request();
        request.setRequestCircleName(circleName);
        request.setRequestCircleContent(circleContent);
        request.setRequestType(0);//0标记建立圈子申请
        request.setRequestTime(MyTime.getNowTime());
        request.setRequest_UserId(setId);
        return requestDao.insertRequest(request) > 1;
    }

    //    p)	任命新圈主（需要向管理员申请）【在request、circle中】
    @Override//circleUserId新圈主id，发起人setId（原圈主）
    public boolean setRequest1(int circleId, String circleUserId, String setId) {
        if (circleDao.IsCircleMaster(setId, circleId) != null)//setId是原圈主
        {
            Request request = new Request();
            request.setRequestCircleId(circleId);
            request.setRequestType(1);//1标记任命新圈主申请
            request.setRequestTime(MyTime.getNowTime());
            request.setRequest_UserId(setId);
            return requestDao.insertRequest(request) > 1;
        } else
            return false;//setId不是是原圈主
    }

    //    q)	修改圈子相关信息（需要向管理员申请）【在request、circle中】
    @Override
    public boolean setRequest2(int circleId, String circleName, String circleContent, String setId) {
        //先处理内容中的换行和空格
        circleContent=circleContent.replace("&nbsp"," ");
        circleContent=circleContent.replace("<br>","\n");
        Request request = new Request();
        request.setRequestCircleId(circleId);
        request.setRequestCircleName(circleName);
        request.setRequestCircleContent(circleContent);
        request.setRequestType(2);//2标记修改圈子相关信息申请
        request.setRequestTime(MyTime.getNowTime());
        request.setRequest_UserId(setId);
        return requestDao.insertRequest(request) > 1;
    }

    //    c)	查看消息（举报，请求（建立圈子，修改圈子信息，任命新圈主））【在report，request中】
    @Override
    public List<Request> getRequest() {
        return requestDao.selectAllOrder();
    }
}
