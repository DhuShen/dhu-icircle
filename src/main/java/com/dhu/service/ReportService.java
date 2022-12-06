package com.dhu.service;

import com.dhu.domain.Report;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ReportService {
    //    e)	举报用户（帖子下、评论下）【在Report中】
    boolean reportByUserId(String setId, String getId, String reportContent);//查看别的用户主页时,举报用户

    boolean reportByPostId(String setId, int postId, String reportContent);//举报帖子

    boolean reportByDiscussId(String setId, int discussId, String reportContent);//举报评论

    //    c)	查看消息（举报，请求（建立圈子，修改圈子信息，任命新圈主））【在report，request中】
    List<Report> getReport();
}
