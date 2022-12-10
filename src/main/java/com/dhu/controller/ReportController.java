package com.dhu.controller;

import com.dhu.domain.User;
import com.dhu.service.ReportService;
import com.dhu.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("api/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    //举报用户
    @RequestMapping("reportUser")
    public Result<Boolean> reportUser(@RequestParam String userId, @RequestParam String content, HttpSession session) {
        User user = (User) session.getAttribute("user");
        boolean flag = reportService.reportByUserId(user.getUserId(), userId, content);
        return new Result<>(flag ? Result.SAVE_OK : Result.SAVE_ERR, flag, null);
    }
}
