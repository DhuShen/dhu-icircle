package com.dhu.controller;

import com.dhu.domain.Discuss;
import com.dhu.service.DiscussService;
import com.dhu.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/api/discuss")
public class DiscussController {
    @Autowired
    DiscussService discussService;

    //根据帖子id获取所有评论
    @RequestMapping("getDiscuss")
    public Result<List<Discuss>> getDiscuss(@RequestParam Integer postId) {
        List<Discuss> discusses = discussService.selectPostDiscuss(postId);
        return new Result<>(Result.GET_OK, discusses, null);
    }
}
