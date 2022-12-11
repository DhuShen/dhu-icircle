package com.dhu.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Report {

    private int reportId;
    private String reportContent;
    private int reportType;
    private String reportUserIdGet;
    private int reportPostId;
    private int reportDiscussId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date reportTime;
    private String reportUserIdSet;

    private int reportLife;

    public Report() {
        reportLife = 0;
    }

    public int getReportLife() {
        return reportLife;
    }

    public void setReportLife(int reportLife) {
        this.reportLife = reportLife;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }


    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }


    public int getReportType() {
        return reportType;
    }

    public void setReportType(int reportType) {
        this.reportType = reportType;
    }


    public String getReportUserIdGet() {
        return reportUserIdGet;
    }

    public void setReportUserIdGet(String reportUserIdGet) {
        this.reportUserIdGet = reportUserIdGet;
    }


    public int getReportPostId() {
        return reportPostId;
    }

    public void setReportPostId(int reportPostId) {
        this.reportPostId = reportPostId;
    }


    public int getReportDiscussId() {
        return reportDiscussId;
    }

    public void setReportDiscussId(int reportDiscussId) {
        this.reportDiscussId = reportDiscussId;
    }


    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }


    public String getReportUserIdSet() {
        return reportUserIdSet;
    }

    public void setReportUserIdSet(String reportUserIdSet) {
        this.reportUserIdSet = reportUserIdSet;
    }

}
