package com.dhu.domain;


public class Report {

  private long reportId;
  private String reportContent;
  private long reportType;
  private String reportUserIdGet;
  private long reportPostId;
  private long reportDiscussId;
  private java.sql.Timestamp reportTime;
  private String reportUserIdSet;


  public long getReportId() {
    return reportId;
  }

  public void setReportId(long reportId) {
    this.reportId = reportId;
  }


  public String getReportContent() {
    return reportContent;
  }

  public void setReportContent(String reportContent) {
    this.reportContent = reportContent;
  }


  public long getReportType() {
    return reportType;
  }

  public void setReportType(long reportType) {
    this.reportType = reportType;
  }


  public String getReportUserIdGet() {
    return reportUserIdGet;
  }

  public void setReportUserIdGet(String reportUserIdGet) {
    this.reportUserIdGet = reportUserIdGet;
  }


  public long getReportPostId() {
    return reportPostId;
  }

  public void setReportPostId(long reportPostId) {
    this.reportPostId = reportPostId;
  }


  public long getReportDiscussId() {
    return reportDiscussId;
  }

  public void setReportDiscussId(long reportDiscussId) {
    this.reportDiscussId = reportDiscussId;
  }


  public java.sql.Timestamp getReportTime() {
    return reportTime;
  }

  public void setReportTime(java.sql.Timestamp reportTime) {
    this.reportTime = reportTime;
  }


  public String getReportUserIdSet() {
    return reportUserIdSet;
  }

  public void setReportUserIdSet(String reportUserIdSet) {
    this.reportUserIdSet = reportUserIdSet;
  }

}
