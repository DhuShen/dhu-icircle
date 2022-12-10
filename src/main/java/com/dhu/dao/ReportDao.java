package com.dhu.dao;

import com.dhu.domain.Report;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//转换为bean（数据库访问层）
public interface ReportDao {
    //增加举报
    int InsertReport(Report report);
    //查询未审核的举报
    List<Report> selectAllOrder();

    //查询已经审核的举报
    List<Report> selectAllOrderChecked();
    //设置审核举报
    boolean setReportChecked(@Param("reportId") int reportId);

    Report selectReportById(@Param("reportId")int reportId);
}
