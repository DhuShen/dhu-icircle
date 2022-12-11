package com.dhu.service;

import com.dhu.domain.Report;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ReportService {
    //根据reportId获取举报信息
    Report getReportById(int reportId);
    //    e)	举报用户（帖子下、评论下）【在Report中】
    boolean reportByUserId(String setId, String getId, String reportContent);//查看别的用户主页时,举报用户

    boolean reportByPostId(String setId, int postId, String reportContent);//举报帖子

    boolean reportByDiscussId(String setId, int discussId, String reportContent);//举报评论

    //    c)	查看消息（举报，请求（建立圈子，修改圈子信息，任命新圈主））【在report，request中】
    //获取已审核举报
    List<Report> getReport();
    //获取未审核举报
    List<Report> getReportChecked();
    //设置已审核举报
    boolean setReportChecked(int reportId);

    //设置退回举报
    boolean setReportBack(int reportId);

    //批准举报
    int allowReport(int reportId);

    //退回举报
    boolean refuseReport(int reportId);
}
