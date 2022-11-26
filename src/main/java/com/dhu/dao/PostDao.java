package com.dhu.dao;

import com.dhu.domain.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//转换为bean（数据库访问层）
public interface PostDao {
    List<Post> selectByName(@Param("postName") String postName);

    List<Post> selectTen();

    List<Post> selectByCircle(@Param("circleId") long circleId);

    List<Post> selectByUser(@Param("userId") String userId);

    int likePost(@Param("postId")long postId);

    Post selectById(@Param("postId")long postId);

    int setPostKey(@Param("postId")long postId);

    int cancelPostKey(@Param("postId")long postId);

    int cancelPost(@Param("postId")long postId);
}
