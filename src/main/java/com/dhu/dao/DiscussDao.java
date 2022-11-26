package com.dhu.dao;

import com.dhu.domain.Discuss;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository//转换为bean（数据库访问层）
public interface DiscussDao {
    int insertDiscuss(Discuss discuss);
    int likeDiscuss(@Param("discussId") long discussId);
    int deleteDiscuss(@Param("discussId") long discussId);
}
