package com.dhu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Request {

  private int requestId;
  private int requestCircleId;
  private String requestCircleName;
  private String requestCircleContent;
  private int requestType;
  private String requestUserId;
  @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
  private Date requestTime;
  private String Request_UserId;

  private int requestLife;

  public Request()
  {
    requestLife=0;
  }

  public int getRequestLife() {
    return requestLife;
  }

  public void setRequestLife(int requestLife) {
    this.requestLife = requestLife;
  }

  public int getRequestId() {
    return requestId;
  }

  public void setRequestId(int requestId) {
    this.requestId = requestId;
  }


  public int getRequestCircleId() {
    return requestCircleId;
  }

  public void setRequestCircleId(int requestCircleId) {
    this.requestCircleId = requestCircleId;
  }


  public String getRequestCircleName() {
    return requestCircleName;
  }

  public void setRequestCircleName(String requestCircleName) {
    this.requestCircleName = requestCircleName;
  }


  public String getRequestCircleContent() {
    return requestCircleContent;
  }

  public void setRequestCircleContent(String requestCircleContent) {
    this.requestCircleContent = requestCircleContent;
  }


  public int getRequestType() {
    return requestType;
  }

  public void setRequestType(int requestType) {
    this.requestType = requestType;
  }


  public String getRequestUserId() {
    return requestUserId;
  }

  public void setRequestUserId(String requestUserId) {
    this.requestUserId = requestUserId;
  }


  public Date getRequestTime() {
    return requestTime;
  }

  public void setRequestTime(Date requestTime) {
    this.requestTime = requestTime;
  }


  public String getRequest_UserId() {
    return Request_UserId;
  }

  public void setRequest_UserId(String request_UserId) {
    this.Request_UserId = request_UserId;
  }

}
