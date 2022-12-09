package com.dhu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Discuss {

  private long discussId;
  private String discussContent;
  private String discussReplayed;
  private long discussGood;
  @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
  private Date discussTime;
  private String discuss_UserId;
  private long discuss_PostId;


  public long getDiscussId() {
    return discussId;
  }

  public void setDiscussId(long discussId) {
    this.discussId = discussId;
  }


  public String getDiscussContent() {
    return discussContent;
  }

  public void setDiscussContent(String discussContent) {
    this.discussContent = discussContent;
  }


  public String getDiscussReplayed() {
    return discussReplayed;
  }

  public void setDiscussReplayed(String discussReplayed) {
    this.discussReplayed = discussReplayed;
  }


  public long getDiscussGood() {
    return discussGood;
  }

  public void setDiscussGood(long discussGood) {
    this.discussGood = discussGood;
  }


  public Date getDiscussTime() {
    return discussTime;
  }

  public void setDiscussTime(Date discussTime) {
    this.discussTime = discussTime;
  }


  public String getDiscuss_UserId() {
    return discuss_UserId;
  }

  public void setDiscuss_UserId(String discussUserId) {
    this.discuss_UserId = discussUserId;
  }


  public long getDiscuss_PostId() {
    return discuss_PostId;
  }

  public void setDiscuss_PostId(long discussPostId) {
    this.discuss_PostId = discussPostId;
  }

}
