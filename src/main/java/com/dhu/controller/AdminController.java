package com.dhu.controller;

import com.dhu.domain.Admin;
import com.dhu.service.AdminService;
import com.dhu.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    //登录
    @RequestMapping("/login")
    public Result<Boolean> login(@RequestParam String id, @RequestParam String password, HttpSession session) {
        Admin admin = adminService.login(id, password);
        if (admin != null) {
            session.setAttribute("admin", admin);
            session.removeAttribute("user");
            return new Result<>(Result.GET_OK, true, null);
        }
        return new Result<>(Result.GET_ERR, false, null);
    }

    //获取我的信息
    @RequestMapping("/myInfo")
    public Result<Admin> getMyInfo(HttpSession session) {
        Admin admin =  (Admin) session.getAttribute("admin");
        return new Result<>(Result.GET_OK, admin, null);
    }

    //登出
    @RequestMapping("/out")
    public Result<Boolean> out(HttpSession session) {
        session.removeAttribute("admin");
        return new Result<>(Result.UPDATE_OK, true, null);
    }
}
