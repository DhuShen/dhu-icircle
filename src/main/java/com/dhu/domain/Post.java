package com.dhu.domain;


import java.util.Date;

public class Post {

  private long postId;
  private String postName;
  private String postContent;
  private long postKey;
  private long postGood;
  private Date postTime;
  private String post_UserId;
  private long post_CircleId;


  public long getPostId() {
    return postId;
  }

  public void setPostId(long postId) {
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


  public long getPostKey() {
    return postKey;
  }

  public void setPostKey(long postKey) {
    this.postKey = postKey;
  }


  public long getPostGood() {
    return postGood;
  }

  public void setPostGood(long postGood) {
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


  public long getPost_CircleId() {
    return post_CircleId;
  }

  public void setPost_CircleId(long postCircleId) {
    this.post_CircleId = postCircleId;
  }

}
