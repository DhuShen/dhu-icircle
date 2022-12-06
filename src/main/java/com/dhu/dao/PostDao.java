package com.dhu.dao;

import com.dhu.domain.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//转换为bean（数据库访问层）
public interface PostDao {
    //根据PostId查帖子
    List<Post> selectByName(@Param("postName") String postName);
    //查询热门帖子（按热度排序）
    List<Post> selectTen();
    //根据CircleId查帖子
    List<Post> selectByCircle(@Param("circleId") int circleId);
    //根据UserId查帖子
    List<Post> selectByUser(@Param("userId") String userId);
    //点赞帖子
    int likePost(@Param("postId")int postId);
    //根据postId查帖子
    Post selectById(@Param("postId")int postId);
    //设置为精华帖
    int setPostKey(@Param("postId")int postId);
    //取消精华帖
    int cancelPostKey(@Param("postId")int postId);
    //撤销帖子
    int cancelPost(@Param("postId")int postId);
    //增加帖子
    int insertPost(Post post);
}
