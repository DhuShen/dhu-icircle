package com.dhu.service.impl;

import com.dhu.dao.MessageDao;
import com.dhu.dao.ReportDao;
import com.dhu.dao.UserDao;
import com.dhu.domain.Message;
import com.dhu.domain.Report;
import com.dhu.domain.Request;
import com.dhu.service.ReportService;
import com.dhu.tools.MyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportDao reportDao;
    UserDao userDao;
    MessageDao messageDao;

    //根据reportId获取举报信息

    @Override
    public Report getReportById(int reportId) {
        return reportDao.selectReportById(reportId);
    }

    //    e)	举报用户（帖子下、评论下）【在Report中】
    @Override
    public boolean reportByUserId(String setId, String getId, String reportContent) {//使用UserId,举报信息举报用户
        Report report = new Report();
        report.setReportType(0);//0举报用户，1举报帖子，2举报评论
        report.setReportContent(reportContent);//举报内容
        report.setReportUserIdGet(getId);//被举报人
        report.setReportUserIdSet(setId);//举报人
        report.setReportTime(MyTime.getNowTime());
        return reportDao.InsertReport(report) > 0;
    }

    //    e)	举报用户（帖子下、评论下）【在Report中】
    @Override
    public boolean reportByPostId(String setId, int postId, String reportContent) {//使用PostId,举报信息为举报用户的帖子
        Report report = new Report();
        report.setReportType(1);//0举报用户，1举报帖子，2举报评论
        report.setReportContent(reportContent);//举报内容
        report.setReportPostId(postId);//被举报帖子id
        report.setReportUserIdSet(setId);//举报人
        report.setReportTime(MyTime.getNowTime());
        return reportDao.InsertReport(report) > 0;
    }

    //    e)	举报用户（帖子下、评论下）【在Report中】
    @Override
    public boolean reportByDiscussId(String setId, int discussId, String reportContent) {//使用DiscussId,举报信息为举报用户的评论
        Report report = new Report();
        report.setReportType(2);//0举报用户，1举报帖子，2举报评论
        report.setReportContent(reportContent);//举报内容
        report.setReportDiscussId(discussId);//被举报评论id
        report.setReportUserIdSet(setId);//举报人
        report.setReportTime(MyTime.getNowTime());
        return reportDao.InsertReport(report) > 0;
    }

    //    c)	查看消息（举报，请求（建立圈子，修改圈子信息，任命新圈主））【在report，request中】
    //查询未审核举报
    @Override
    public List<Report> getReport() {
        return reportDao.selectAllOrder();

    }

    //查询已审核举报
    @Override
    public List<Report> getReportChecked() {
        return reportDao.selectAllOrderChecked();
    }

    //设置已审核举报
    @Override
    public boolean checkReport(int reportId) {
        return reportDao.setReportChecked(reportId);
    }

    //批准举报（）
    public boolean allowReport(int reportId) {
        Report report = reportDao.selectReportById(reportId);
        //封号
        if (report.getReportId() == 0) {
            userDao.closeUser(report.getReportUserIdGet());
        }
        //发信息给举报人
        Message message = new Message();
        message.setMessageUserIdSet(null);
        message.setMessageContent("您的举报 用户" + report.getReportUserIdGet() + " 已举报成功！");
        message.setMessageUserIdGet(report.getReportUserIdSet());
        message.setMessageSetTime(MyTime.getNowTime());
        if (messageDao.insertMessage(message) < 1) return false;

        //发信息给被举报人
        Message message1 = new Message();
        message1.setMessageUserIdSet(null);
        message1.setMessageContent("您被举报成功，将进行封号处理！");
        message1.setMessageUserIdGet(report.getReportUserIdGet());
        message1.setMessageSetTime(MyTime.getNowTime());
        if (messageDao.insertMessage(message1) < 1) return false;
        //设置已审核
        return checkReport(reportId);
    }

    //退回举报
    public boolean refuseReport(int reportId) {
        Report report = reportDao.selectReportById(reportId);
        //发信息给举报人
        Message message = new Message();
        message.setMessageUserIdSet(null);
        message.setMessageContent("您的举报 用户" + report.getReportUserIdGet() + " 经检测未违规，举报失败！");
        message.setMessageUserIdGet(report.getReportUserIdSet());
        message.setMessageSetTime(MyTime.getNowTime());
        if (messageDao.insertMessage(message) < 1) return false;
        //设置已审核
        return checkReport(reportId);
    }
}
