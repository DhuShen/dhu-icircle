package com.dhu.domain;

import java.security.Timestamp;

public class Circle {

    private long circleId;
    private String circleName;
    private String circleImg;
    private String circleContent;
    private long circleLife;
    private java.sql.Timestamp circleTime;
    private String circle_UserId;


    public long getCircleId() {
        return circleId;
    }

    public void setCircleId(long circleId) {
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


    public long getCircleLife() {
        return circleLife;
    }

    public void setCircleLife(long circleLife) {
        this.circleLife = circleLife;
    }


    public java.sql.Timestamp getCircleTime() {
        return circleTime;
    }

    public void setCircleTime(java.sql.Timestamp circleTime) {
        this.circleTime = circleTime;
    }


    public String getCircle_UserId() {
        return circle_UserId;
    }

    public void setCircle_UserId(String circleUserId) {
        this.circle_UserId = circleUserId;
    }

}
