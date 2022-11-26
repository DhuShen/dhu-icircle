package com.dhu.service;

import com.dhu.domain.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PostService {
    //	用户主页
//    c)	查找圈子、贴子
    List<Post> searchPosts(String postName);

    //    d)	浏览圈子、帖子、评论
    List<Post> getHotPosts();//查找热门帖子排序

    List<Post> getCirclePosts(long circleId);//查找帖子（圈子id）

    List<Post> getMyPosts(String userId);//查找我的帖子（用户id）

    //    g)	点赞帖子、评论(在post，discuss)
    boolean likePost(long postId);//点赞（帖子id）

    //    r)	添加圈子的精华帖（在post中）
    boolean setPostKey(long postId);

    //    s)	取消圈子的精华帖（在post中）
    boolean cancelPostKey(long postId);
    //    t)	撤销圈内成员的帖子/评论（在post，discuss中）
    boolean CancelPost(long postId);
}