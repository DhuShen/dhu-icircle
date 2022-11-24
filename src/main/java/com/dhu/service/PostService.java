package com.dhu.service;

import com.dhu.domain.Post;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface PostService {
    //查找热门帖子，10个即可
    List<Post> getHotPosts();
}