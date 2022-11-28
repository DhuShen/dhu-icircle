package com.dhu.controller;

import com.dhu.domain.Post;
import com.dhu.service.PostService;
import com.dhu.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("api/post")
public class PostController {
    @Autowired
    PostService postService;

    @RequestMapping("/hotposts")
    public Result<List<Post>> HotPosts() {
        return new Result<>(Result.GET_OK, postService.getHotPosts(), null);
    }
}
