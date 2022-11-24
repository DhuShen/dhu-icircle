package com.dhu.dao;

import com.dhu.domain.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository//转换为bean（数据库访问层）
public interface AdminDao {
    Admin adminSelectById(@Param("id")String id);
}
