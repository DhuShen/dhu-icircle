package com.dhu.controller;

import com.dhu.domain.User;
import com.dhu.service.RequestService;
import com.dhu.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
@RequestMapping("api/request")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @RequestMapping("/create")
    public Result<Boolean> requestCreateCircle(@RequestParam String circleName, @RequestParam String circleContent, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return new Result<>(Result.SAVE_ERR, false, null);
        } else {
            boolean flag = requestService.setRequest0(circleName, circleContent, user.getUserId());
            return new Result<>(flag ? Result.SAVE_OK : Result.SAVE_ERR, flag, null);
        }
    }
}
