package com.dhu.service;

import com.dhu.domain.Report;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;;

@Transactional
public interface ReportService {
    //    e)	举报用户（帖子下、评论下）
    boolean reportByUserId(String setId, String getId, String reportContent);//查看别的用户主页时

    boolean reportByPostId(String setId, long postId, String reportContent);

    boolean reportByDiscussId(String setId, long discussId, String reportContent);
}
