package TestServiceImpl;

import com.dhu.dao.DiscussDao;
import com.dhu.domain.Discuss;
import com.dhu.service.DiscussService;
import com.dhu.tools.MyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussServiceImpl implements DiscussService {
    @Autowired
    DiscussDao discussDao;

    //根据id获取评论
    @Override
    public Discuss selectById(int discussId) {
        return discussDao.selectById(discussId);
    }

    //    d)	浏览圈子、帖子、评论【在Circle,Post,Discuss中】--查询帖子下评论
    @Override
    public List<Discuss> selectPostDiscuss(int postId) {
        return discussDao.selectPostDiscuss(postId);
    }

    //    f)	在帖子下发布评论【在Discuss中】
    @Override
    public boolean discussInPost(String setId, int postId, String discussContent, String discussReplayed) {
        Discuss discuss = new Discuss();
        discuss.setDiscuss_UserId(setId);//评论人
        discuss.setDiscuss_PostId(postId);//帖子id
        discuss.setDiscussContent(discussContent);//评论内容
        discuss.setDiscussReplayed(discussReplayed);//被回复评论的人的名字
        discuss.setDiscussGood(0);
        discuss.setDiscussTime(MyTime.getNowTime());
        return discussDao.insertDiscuss(discuss) > 0;
    }

    //    g)	点赞帖子、评论【在post，discuss中】
    @Override
    public boolean likeDiscuss(int discussId) {
        return discussDao.likeDiscuss(discussId) > 0;
    }

    //    t)	撤销圈内成员的帖子/评论【在post，discuss中】--仅圈主可用--AOP
    @Override
    public boolean CancelDiscuss(int discussId) {
        return discussDao.deleteDiscuss(discussId) > 0;
    }
}
