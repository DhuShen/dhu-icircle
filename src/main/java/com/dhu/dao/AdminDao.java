package com.dhu.dao;

import com.dhu.domain.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository//转换为bean（数据库访问层）
public interface AdminDao {
    //根据AdminId查询管理员信息
    Admin adminSelectById(@Param("adminId")String adminId);
    //添加管理员
    int adminInsert(Admin admin);
}
