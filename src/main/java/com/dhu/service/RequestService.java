package com.dhu.service;

import com.dhu.domain.Report;
import com.dhu.domain.Request;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RequestService {
    //    k)	建立圈子（需要向管理员申请）【在request、circle中】
    boolean setRequest0(String circleName, String circleContent, String setId);

    //    p)	任命新圈主（需要向管理员申请）【在request、circle中】
    boolean setRequest1(int circleId, String circleUserId, String setId);

    //    q)	修改圈子相关信息（需要向管理员申请）【在request、circle中】
    boolean setRequest2(int circleId, String circleName, String circleContent, String setId);

    //    c)	查看消息（举报，请求（建立圈子，修改圈子信息，任命新圈主））【在report，request中】
    //查询未审核请求
    List<Request> getRequest();

    //查询已审核请求
    List<Report> getRequestChecked();

    //设置已审核请求
    boolean checkRequest(@Param("requestId") int requestId);
}
