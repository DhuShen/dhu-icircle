package com.dhu.controller;

import com.dhu.domain.Request;
import com.dhu.domain.User;
import com.dhu.service.CircleService;
import com.dhu.service.RequestService;
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
@RequestMapping("api/request")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @Autowired
    private CircleService circleService;
    //建立圈子
    @RequestMapping("/create")
    public Result<Boolean> requestCreateCircle(@RequestParam String circleName, @RequestParam String circleContent, HttpSession session) {
        User user = (User) session.getAttribute("user");
        boolean flag = requestService.setRequest0(circleName, circleContent, user.getUserId());
        return new Result<>(flag ? Result.SAVE_OK : Result.SAVE_ERR, flag, null);
    }
    //更改圈子
    @RequestMapping("/update")
    public Result<Boolean> updateCircle(@RequestParam Integer circleId, @RequestParam String circleName, @RequestParam String circleContent, HttpSession session) {
        User user = (User) session.getAttribute("user");
        boolean flag = requestService.setRequest2(circleId, circleName, circleContent, user.getUserId());
        return new Result<>(flag ? Result.SAVE_OK : Result.SAVE_ERR, flag, null);
    }

    //查询所有未审批请求
    @RequestMapping("/getRequest")
    public Result<List<Request>> getRequest() {
        List<Request> requests = requestService.getRequest();
        return new Result<>(Result.GET_OK, requests, null);
    }

    //查询所有完成的请求
    @RequestMapping("/getCheckedRequest")
    public Result<List<Request>> getCheckedRequest() {
        List<Request> requests = requestService.getRequestChecked();
        return new Result<>(Result.GET_OK, requests, null);
    }

    //退回
    @RequestMapping("/refuse")
    public Result<Boolean> refuse(@RequestParam Integer requestId, @RequestParam Integer type) {
        boolean flag = switch (type) {
            case 0 -> circleService.refuseSetUpCircle(requestId);
            case 1 -> circleService.refuseUpdateCircleContent(requestId);
            default -> false;
        };
        return new Result<>(flag ? Result.UPDATE_OK : Result.UPDATE_ERR, flag, null);
    }

    //通过
    @RequestMapping("/agree")
    public Result<Boolean> agree(@RequestParam Integer requestId, @RequestParam Integer type) {
        boolean flag = switch (type) {
            case 0 -> circleService.allowSetUpCircle(requestId);
            case 1 -> circleService.allowUpdateCircleContent(requestId);
            default -> false;
        };
        return new Result<>(flag ? Result.UPDATE_OK : Result.UPDATE_ERR, flag, null);
    }
}
