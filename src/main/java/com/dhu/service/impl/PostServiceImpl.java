package com.dhu.service.impl;

import com.dhu.dao.CircleDao;
import com.dhu.dao.PostDao;
import com.dhu.domain.Circle;
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

    @Autowired
    CircleDao circleDao;

    @Override
    public int getPostCount(int circleId) {
        return postDao.getPostCount(circleId);
    }

    //根据id查询帖子
    @Override
    public Post selectById(int postId) {
        return postDao.selectById(postId);
    }

    //    c)	查找圈子、贴子【在Circle,Post中】-模糊查询
    @Override
    public List<Post> searchPosts(String postName) {
        return postDao.selectByName(postName);
    }

    //    d)	浏览圈子、帖子、评论【在Circle,Post,Discuss中】--查找热门帖子排序
    @Override
    public List<Post> getHotPosts() {
        return postDao.selectTen();
    }

    //    d)	浏览圈子、帖子、评论【在Circle,Post,Discuss中】-- 获取圈子中的所有帖子
    @Override
    public List<Post> getCirclePosts(int circleId) {
        return postDao.selectByCircle(circleId);
    }

    //    d)	浏览圈子、帖子、评论【在Circle,Post,Discuss中】-- 获取圈子中的所有精华帖子
    @Override
    public List<Post> getCircleKeyPosts(int circleId) {
        return postDao.selectKeyByCircle(circleId);
    }

    //    g)	点赞帖子、评论【在post，discuss中】
    @Override
    public boolean likePost(int postId) {
        return postDao.likePost(postId) > 0;
    }

    //    m)	在圈子中发布帖子【在post中】s
    @Override
    public boolean insertPost(String postName, String postContent, String userId, int circleId) {
        //先处理内容中的换行和空格
        postContent = postContent.replace(" ", "&nbsp;");
        postContent = postContent.replace("\n", "<br>");
        Post post = new Post();
        post.setPostName(postName);
        post.setPostContent(postContent);
        post.setPostKey(0);
        post.setPostGood(0);
        post.setPostTime(MyTime.getNowTime());
        post.setPost_UserId(userId);
        post.setPost_CircleId(circleId);
        return postDao.insertPost(post) > 0;
    }

    //   i5)查看自己的帖子【在post中】
    @Override
    public List<Post> getMyPosts(String userId) {
        return postDao.selectByUser(userId);
    }

    //    r)	添加圈子的精华帖【在post中】--必须是圈主-AOP实现
    @Override
    public boolean setPostKey(int postId) {
        Post post = postDao.selectById(postId);
        if (post.getPostKey() != 0) {
            throw new MyException("已是精华帖!");
        } else
            return postDao.setPostKey(postId) > 0;
    }

    //    s)	取消圈子的精华帖【在post中】--必须是圈主-AOP实现
    @Override
    public boolean cancelPostKey(int postId) {
        Post post = postDao.selectById(postId);
        if (post.getPostKey() == 0) {
            throw new MyException("不是精华帖!");
        } else
            return postDao.cancelPostKey(postId) > 0;
    }

    //    t)	撤销圈内成员的帖子/评论【在post，discuss中】--必须是圈主-AOP实现
    @Override
    public boolean CancelPost(int postId) {
        return postDao.cancelPost(postId) > 0;
    }
}
