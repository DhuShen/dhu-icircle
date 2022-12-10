package com.dhu.controller;

import com.dhu.domain.User;
import com.dhu.service.RequestService;
import com.dhu.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("api/request")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @RequestMapping("/create")
    public Result<Boolean> requestCreateCircle(@RequestParam String circleName, @RequestParam String circleContent, HttpSession session) {
        User user = (User) session.getAttribute("user");
        boolean flag = requestService.setRequest0(circleName, circleContent, user.getUserId());
        return new Result<>(flag ? Result.SAVE_OK : Result.SAVE_ERR, flag, null);
    }

    //更改圈子
    @RequestMapping("/update")
    public Result<Boolean> requestUpdateCircle(@RequestParam Integer circleId, @RequestParam String circleName, @RequestParam String circleContent, HttpSession session) {
        User user = (User) session.getAttribute("user");
        boolean flag = requestService.setRequest2(circleId, circleName, circleContent, user.getUserId());
        return new Result<>(flag ? Result.SAVE_OK : Result.SAVE_ERR, flag, null);
    }
}
