package com.dhu.domain;

import java.util.Date;

public class Request {

  private long requestId;
  private long requestCircleId;
  private String requestCircleName;
  private String requestCircleContent;
  private long requestType;
  private String requestUserId;
  private Date requestTime;
  private String Request_UserId;


  public long getRequestId() {
    return requestId;
  }

  public void setRequestId(long requestId) {
    this.requestId = requestId;
  }


  public long getRequestCircleId() {
    return requestCircleId;
  }

  public void setRequestCircleId(long requestCircleId) {
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


  public long getRequestType() {
    return requestType;
  }

  public void setRequestType(long requestType) {
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
