package com.dhu.controller;

import com.dhu.domain.Post;
import com.dhu.service.PostService;
import com.dhu.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("api/post")
public class PostController {
    @Autowired
    private PostService postService;

    //获取热帖
    @RequestMapping("/hotposts")
    public Result<List<Post>> hotPosts() {
        List<Post> posts = postService.getHotPosts();
        return new Result<>(Result.GET_OK, postService.getHotPosts(), null);
    }

    //获取帖子信息
    @RequestMapping("/getInfo")
    public Result<Post> getInfo(@RequestParam Integer postId) {
        Post post = postService.selectById(postId);
        if (post!=null)
            return new Result<>(Result.GET_OK, post, null);
        else
            return new Result<>(Result.GET_ERR,null,"查询帖子失败");
    }

    //点赞
    @RequestMapping("/good")
    public Result<Boolean> good(@RequestParam Integer postId) {
        boolean flag = postService.likePost(postId);
        return new Result<>(flag ? Result.UPDATE_OK : Result.UPDATE_ERR, flag, null);
    }
}
