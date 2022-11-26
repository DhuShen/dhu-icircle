package com.dhu.dao;

import com.dhu.domain.Report;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository//转换为bean（数据库访问层）
public interface ReportDao {
    int InsertReport(Report report);
}
