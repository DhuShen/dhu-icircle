package com.dhu.domain;

import com.dhu.service.staticService.MyTime;

import java.util.Date;

public class User {

  private String userId;
  private String userPassword;
  private String userImg;
  private String userName;
  private String userSex;
  private long userLife;
  private Date userTime;
  private String userMajor;
  private String userIntroduction;
  //生成新用户
  public User() {
  }
  public User(String userId,String userPassword)
  {
    this.userId=userId;
    this.userPassword=userPassword;
    this.userName="用户"+userId;
    this.userSex=null;
    this.userImg=null;
    this.userLife=0;//初始化为未封号0
    this.userTime= MyTime.getNowTime();
    this.userMajor=null;
    this.userIntroduction=null;
  }



  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  public String getUserImg() {
    return userImg;
  }

  public void setUserImg(String userImg) {
    this.userImg = userImg;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserSex() {
    return userSex;
  }

  public void setUserSex(String userSex) {
    this.userSex = userSex;
  }


  public long getUserLife() {
    return userLife;
  }

  public void setUserLife(long userLife) {
    this.userLife = userLife;
  }


  public Date getUserTime() {
    return userTime;
  }

  public void setUserTime(Date userTime) {
    this.userTime = userTime;
  }


  public String getUserMajor() {
    return userMajor;
  }

  public void setUserMajor(String userMajor) {
    this.userMajor = userMajor;
  }


  public String getUserIntroduction() {
    return userIntroduction;
  }

  public void setUserIntroduction(String userIntroduction) {
    this.userIntroduction = userIntroduction;
  }

}
