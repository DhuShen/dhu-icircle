package com.dhu.dao;

import com.dhu.domain.Report;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//转换为bean（数据库访问层）
public interface ReportDao {
    //增加举报
    int InsertReport(Report report);
    //以被举报人id排序查询所有举报信息
    List<Report> selectAllOrder();
    //设置审核举报
    boolean setReportChecked(@Param("reportId") int reportId);
}
