package com.dhu.service.impl;

import com.dhu.dao.ReportDao;
import com.dhu.domain.Report;
import com.dhu.service.ReportService;
import com.dhu.service.StaticService.MyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportDao reportDao;

    @Override
    public boolean reportByUserId(String setId, String getId, String reportContent) {//使用UserId,举报信息举报用户
        Report report = new Report();
        report.setReportType(0);//0举报用户，1举报帖子，2举报评论
        report.setReportContent(reportContent);//举报内容
        report.setReportUserIdGet(getId);//被举报人
        report.setReportUserIdSet(setId);//举报人
        report.setReportTime(MyTime.getNowTime());
        return reportDao.InsertReport(report) != 0;
    }

    @Override
    public boolean reportByPostId(String setId, long postId, String reportContent) {//使用PostId,举报信息为举报用户的帖子
        Report report = new Report();
        report.setReportType(1);//0举报用户，1举报帖子，2举报评论
        report.setReportContent(reportContent);//举报内容
        report.setReportPostId(postId);//被举报帖子id
        report.setReportUserIdSet(setId);//举报人
        report.setReportTime(MyTime.getNowTime());
        return reportDao.InsertReport(report) != 0;
    }

    @Override
    public boolean reportByDiscussId(String setId, long discussId, String reportContent) {//使用DiscussId,举报信息为举报用户的评论
        Report report = new Report();
        report.setReportType(2);//0举报用户，1举报帖子，2举报评论
        report.setReportContent(reportContent);//举报内容
        report.setReportDiscussId(discussId);//被举报评论id
        report.setReportUserIdSet(setId);//举报人
        report.setReportTime(MyTime.getNowTime());
        return reportDao.InsertReport(report) != 0;
    }
}
