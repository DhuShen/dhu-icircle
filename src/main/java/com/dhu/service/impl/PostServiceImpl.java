package com.dhu.service.impl;

import com.dhu.dao.PostDao;
import com.dhu.domain.Post;
import com.dhu.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostDao postDao;

    @Override
    public List<Post> searchPosts(String postName) {
        return postDao.selectByName(postName);
    }

    @Override
    public List<Post> getHotPosts() {
        return postDao.selectTen();
    }

    @Override
    public List<Post> getCirclePosts(long circleId) {
        return postDao.selectByCircle(circleId);
    }

    @Override
    public List<Post> getMyPosts(String userId) {
        return postDao.selectByUser(userId);
    }

    @Override
    public boolean likePost(long postId) {
        return postDao.likePost(postId)>1;
    }
}
