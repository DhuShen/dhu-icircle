package com.dhu.dao;

import com.dhu.domain.Report;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//转换为bean（数据库访问层）
public interface ReportDao {

    int InsertReport(Report report);

    List<Report> selectAllOrder();
}
