package com.dhu.controller;

import com.dhu.domain.Post;
import com.dhu.domain.User;
import com.dhu.service.PostService;
import com.dhu.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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

    //根据圈子Id获取帖子
    @RequestMapping("/getPost")
    public Result<List<Post>> getPost(@RequestParam Integer circleId) {
        List<Post> posts = postService.getCirclePosts(circleId);
        return new Result<>(Result.GET_OK, postService.getCirclePosts(circleId), null);
    }

    //根据圈子Id获取精华帖子
    @RequestMapping("/getKeyPost")
    public Result<List<Post>> getKeyPost(@RequestParam Integer circleId) {
        List<Post> posts = postService.getCircleKeyPosts(circleId);
        return new Result<>(Result.GET_OK,posts, null);
    }

    //获取帖子信息
    @RequestMapping("/getInfo")
    public Result<Post> getInfo(@RequestParam Integer postId) {
        Post post = postService.selectById(postId);
        if (post != null)
            return new Result<>(Result.GET_OK, post, null);
        else
            return new Result<>(Result.GET_ERR, null, "查询帖子失败");
    }

    //点赞
    @RequestMapping("/good")
    public Result<Boolean> good(@RequestParam Integer postId) {
        boolean flag = postService.likePost(postId);
        return new Result<>(flag ? Result.UPDATE_OK : Result.UPDATE_ERR, flag, null);
    }

    //发帖
    @RequestMapping("/add")
    public Result<Boolean> add(@RequestParam String postName, @RequestParam String postContent, @RequestParam Integer circleId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        boolean flag = postService.insertPost(postName, postContent, user.getUserId(), circleId);
        return new Result<>(flag ? Result.SAVE_OK : Result.SAVE_ERR, flag, null);
    }

    //获取精华帖
    @RequestMapping("/setKeyPost")
    public Result<Boolean> setPostKey(@RequestParam Integer postId) {
        boolean flag = postService.setPostKey(postId);
        return new Result<>(flag ? Result.UPDATE_OK : Result.UPDATE_ERR, flag, null);
    }

    //取消精华帖
    @RequestMapping("/cancelKeyPost")
    public Result<Boolean> cancelPostKey(@RequestParam Integer postId) {
        boolean flag = postService.cancelPostKey(postId);
        return new Result<>(flag ? Result.UPDATE_OK : Result.UPDATE_ERR, flag, null);
    }

    @RequestMapping("/search")
    public Result<List<Post>> search(@RequestParam String name) {
        List<Post> posts = postService.searchPosts(name);
        return new Result<>(Result.GET_OK,posts, null);
    }
}
