package com.dhu.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RequestService {
    //    k)	建立圈子（需要向管理员申请）（在request、circle中）
    boolean setRequest0(String circleName, String circleContent, String setId);
    //    p)	任命新圈主（需要向管理员申请）（在request、circle中）
    boolean setRequest1(long circleId, String circleUserId, String setId);
}
