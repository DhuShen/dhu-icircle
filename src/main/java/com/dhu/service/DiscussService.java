package com.dhu.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DiscussService {
//    f)	在帖子下发布评论(在Discuss)
    public boolean discussInPost(String setId, long postId, String discussContent,
                                 String discussReplayed);//评论人id，帖子id，回复人姓名（可为null）
//    g)	点赞帖子、评论(在post，discuss)
    boolean likeDiscuss(long discussId);//点赞（评论id）
}
