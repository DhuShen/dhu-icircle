package com.dhu.domain;


public class Message {

  private long messageId;
  private String messageContent;
  private String messageUserIdSet;
  private String messageUserIdGet;


  public long getMessageId() {
    return messageId;
  }

  public void setMessageId(long messageId) {
    this.messageId = messageId;
  }


  public String getMessageContent() {
    return messageContent;
  }

  public void setMessageContent(String messageContent) {
    this.messageContent = messageContent;
  }


  public String getMessageUserIdSet() {
    return messageUserIdSet;
  }

  public void setMessageUserIdSet(String messageUserIdSet) {
    this.messageUserIdSet = messageUserIdSet;
  }


  public String getMessageUserIdGet() {
    return messageUserIdGet;
  }

  public void setMessageUserIdGet(String messageUserIdGet) {
    this.messageUserIdGet = messageUserIdGet;
  }

}
