package com.dhu.service.impl;

import com.dhu.dao.PostDao;
import com.dhu.domain.Post;
import com.dhu.exception.MyException;
import com.dhu.service.PostService;
import com.dhu.tools.MyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostDao postDao;

    //查找帖子
    @Override
    public List<Post> searchPosts(String postName) {
        return postDao.selectByName(postName);
    }

    //获取热度帖子
    @Override
    public List<Post> getHotPosts() {
        return postDao.selectTen();
    }

    //获取圈子中的所有帖子
    @Override
    public List<Post> getCirclePosts(long circleId) {
        return postDao.selectByCircle(circleId);
    }

    //点赞帖子
    @Override
    public boolean likePost(long postId) {
        return postDao.likePost(postId) > 1;
    }

    //圈子里写帖子
    @Override
    public boolean insertPost(String postName, String postContent, String userId, long circleId) {
        Post post = new Post();
        post.setPostName(postName);
        post.setPostContent(postContent);
        post.setPostKey(0);
        post.setPostGood(0);
        post.setPostTime(MyTime.getNowTime());
        post.setPost_UserId(userId);
        post.setPost_CircleId(circleId);
        return postDao.insertPost(post) > 1;
    }

    //用户个人中心
    //查看我的帖子
    @Override
    public List<Post> getMyPosts(String userId) {
        return postDao.selectByUser(userId);
    }

    //圈主（添加精华帖）
    @Override
    public boolean setPostKey(long postId) {
        Post post = postDao.selectById(postId);
        if (post.getPostKey() != 0) {
            throw new MyException("已是精华帖!");
        } else
            return postDao.setPostKey(postId) > 0;
    }

    //圈主（撤销精华帖）
    @Override
    public boolean cancelPostKey(long postId) {
        Post post = postDao.selectById(postId);
        if (post.getPostKey() == 0) {
            throw new MyException("不是精华帖!");
        } else
            return postDao.cancelPostKey(postId) > 0;
    }

    //圈主（撤销帖子）
    @Override
    public boolean CancelPost(long postId) {
        return postDao.cancelPost(postId) > 0;
    }
}
