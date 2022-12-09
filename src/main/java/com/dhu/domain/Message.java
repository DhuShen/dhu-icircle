package com.dhu.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Message {

    private int messageId;
    private String messageContent;
    private String messageUserIdSet;
    private String messageUserIdGet;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date messageSetTime;

    public Date getMessageSetTime() {
        return messageSetTime;
    }
    public void setMessageSetTime(Date messageSetTime) {
        this.messageSetTime = messageSetTime;
    }



    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
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
