package com.dhu.service;

import com.dhu.domain.Discuss;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DiscussService {
    //根据id获取评论
    Discuss selectById(int discussId);

    //    d)	浏览圈子、帖子、评论【在Circle,Post,Discuss中】
    List<Discuss> selectPostDiscuss(int postId);

    //    f)	在帖子下发布评论【在Discuss中】
    boolean discussInPost(String setId, int postId, String discussContent,
                          String discussReplayed);//评论人id，帖子id，回复人姓名（可为null）

    //    g)	点赞帖子、评论【在post，discuss中】
    boolean likeDiscuss(int discussId);//点赞（评论id）

    //    t)	撤销圈内成员的帖子/评论【在post，discuss中】
    boolean CancelDiscuss(int discussId);
}
