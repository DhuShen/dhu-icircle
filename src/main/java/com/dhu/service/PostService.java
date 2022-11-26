package com.dhu.service;

import com.dhu.domain.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PostService {
    //	用户主页
//    c)	查找圈子、贴子
    List<Post> searchPosts(String name);

    //    d)	浏览圈子、帖子、评论
    List<Post> getHotPosts();//查找热门帖子排序

    List<Post> getCirclePosts(String id);//查找圈子帖子

    List<Post> getMyPosts(String id);//查找我的帖子

}