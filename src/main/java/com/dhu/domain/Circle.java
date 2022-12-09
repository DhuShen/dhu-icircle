package com.dhu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.security.Timestamp;
import java.util.Date;

public class Circle {

    private int circleId;
    private String circleName;
    private String circleImg;
    private String circleContent;
    private int circleLife;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date circleTime;
    private String circle_UserId;


    public int getCircleId() {
        return circleId;
    }

    public void setCircleId(int circleId) {
        this.circleId = circleId;
    }


    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }


    public String getCircleImg() {
        return circleImg;
    }

    public void setCircleImg(String circleImg) {
        this.circleImg = circleImg;
    }


    public String getCircleContent() {
        return circleContent;
    }

    public void setCircleContent(String circleContent) {
        this.circleContent = circleContent;
    }


    public int getCircleLife() {
        return circleLife;
    }

    public void setCircleLife(int circleLife) {
        this.circleLife = circleLife;
    }


    public Date getCircleTime() {
        return circleTime;
    }

    public void setCircleTime(Date circleTime) {
        this.circleTime = circleTime;
    }


    public String getCircle_UserId() {
        return circle_UserId;
    }

    public void setCircle_UserId(String circleUserId) {
        this.circle_UserId = circleUserId;
    }

}
