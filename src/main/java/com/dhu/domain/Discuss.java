package com.dhu.domain;

public class Discuss {

  private long discussId;
  private String discussContent;
  private String discussReplayed;
  private long discussGood;
  private java.sql.Timestamp discussTime;
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


  public java.sql.Timestamp getDiscussTime() {
    return discussTime;
  }

  public void setDiscussTime(java.sql.Timestamp discussTime) {
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
