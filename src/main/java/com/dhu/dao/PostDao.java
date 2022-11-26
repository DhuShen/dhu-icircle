package com.dhu.dao;

import com.dhu.domain.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//转换为bean（数据库访问层）
public interface PostDao {
    List<Post> selectByName(@Param("name") String name);

    List<Post> selectTen();

    List<Post> selectByCircle(@Param("id") String id);

    List<Post> selectByUser(@Param("id") String id);
}
