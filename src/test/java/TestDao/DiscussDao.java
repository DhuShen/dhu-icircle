package TestDao;

import com.dhu.domain.Discuss;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//转换为bean（数据库访问层）
public interface DiscussDao {
    //根据评论id查评论
    Discuss selectById(@Param("discussId") int discussId);
    //添加评论
    int insertDiscuss(Discuss discuss);
    //点赞评论
    int likeDiscuss(@Param("discussId") int discussId);
    //删除评论
    int deleteDiscuss(@Param("discussId") int discussId);
    //根据postId查评论
    List<Discuss> selectPostDiscuss(@Param("postId") int postId);
}
