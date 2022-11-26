package com.dhu.service.impl;

import com.dhu.dao.DiscussDao;
import com.dhu.domain.Discuss;
import com.dhu.service.DiscussService;
import com.dhu.service.staticService.MyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscussServiceImpl implements DiscussService {
    @Autowired
    DiscussDao discussDao;
    @Override
    public boolean discussInPost(String setId, long postId, String discussContent,String discussReplayed) {
        Discuss discuss=new Discuss();
        discuss.setDiscuss_UserId(setId);//评论人
        discuss.setDiscuss_PostId(postId);//帖子id
        discuss.setDiscussContent(discussContent);//评论内容
        discuss.setDiscussReplayed(discussReplayed);//被回复评论的人的名字
        discuss.setDiscussGood(0);
        discuss.setDiscussTime(MyTime.getNowTime());
        return discussDao.insertDiscuss(discuss)>0;
    }

    @Override
    public boolean likeDiscuss(long discussId) {
        return discussDao.likeDiscuss(discussId)>0;
    }
}
