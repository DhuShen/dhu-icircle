package com.dhu.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Post {

  private int postId;
  private String postName;
  private String postContent;
  private int postKey;
  private int postGood;
  @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
  private Date postTime;
  private String post_UserId;
  private int post_CircleId;


  public int getPostId() {
    return postId;
  }

  public void setPostId(int postId) {
    this.postId = postId;
  }


  public String getPostName() {
    return postName;
  }

  public void setPostName(String postName) {
    this.postName = postName;
  }


  public String getPostContent() {
    return postContent;
  }

  public void setPostContent(String postContent) {
    this.postContent = postContent;
  }


  public int getPostKey() {
    return postKey;
  }

  public void setPostKey(int postKey) {
    this.postKey = postKey;
  }


  public int getPostGood() {
    return postGood;
  }

  public void setPostGood(int postGood) {
    this.postGood = postGood;
  }


  public Date getPostTime() {
    return postTime;
  }

  public void setPostTime(Date postTime) {
    this.postTime = postTime;
  }


  public String getPost_UserId() {
    return post_UserId;
  }

  public void setPost_UserId(String postUserId) {
    this.post_UserId = postUserId;
  }


  public int getPost_CircleId() {
    return post_CircleId;
  }

  public void setPost_CircleId(int postCircleId) {
    this.post_CircleId = postCircleId;
  }

}
