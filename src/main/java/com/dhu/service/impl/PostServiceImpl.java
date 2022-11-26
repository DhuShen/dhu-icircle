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
    public List<Post> searchPosts(String name) {
        return postDao.selectByName(name);
    }

    @Override
    public List<Post> getHotPosts() {
        return postDao.selectTen();
    }

    @Override
    public List<Post> getCirclePosts(String id) {
        return postDao.selectByCircle(id);
    }

    @Override
    public List<Post> getMyPosts(String id) {
        return postDao.selectByUser(id);
    }
}
