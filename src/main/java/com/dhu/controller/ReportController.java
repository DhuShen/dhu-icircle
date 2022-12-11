package com.dhu.controller;

import com.dhu.domain.Report;
import com.dhu.domain.User;
import com.dhu.service.ReportService;
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

    //查询未完成
    @RequestMapping("/getReport")
    public Result<List<Report>> getReport() {
        List<Report> reports = reportService.getReport();
        return new Result<>(Result.GET_OK, reports, null);
    }
    //查询已完成
    @RequestMapping("/getCheckedReport")
    public Result<List<Report>> getCheckedReport() {
        List<Report> reports = reportService.getReportChecked();
        return new Result<>(Result.GET_OK, reports, null);
    }

    //拒绝
    @RequestMapping("/refuse")
    public Result<Boolean> refuse(@RequestParam Integer reportId) {
        boolean flag=reportService.refuseReport(reportId);
        return new Result<>(flag ? Result.UPDATE_OK : Result.UPDATE_ERR, flag, null);
    }

    //同意
    @RequestMapping("/agree")
    public Result<Integer> agree(@RequestParam Integer reportId) {
        int count=reportService.allowReport(reportId);
        if(count<=0)
            return new Result<>(Result.UPDATE_ERR,null,"举报处理失败");
        return new Result<>(Result.UPDATE_OK, count, null);
    }
}
