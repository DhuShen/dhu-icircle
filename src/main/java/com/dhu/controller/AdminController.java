package com.dhu.controller;

import com.dhu.domain.Admin;
import com.dhu.service.AdminService;
import com.dhu.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    //登录
    @RequestMapping("/login")
    public void login() {
        System.out.println("in");
        AdminService adminService = new AdminServiceImpl();
        Admin admin=adminService.login("200800118","hhn");
        System.out.println(admin.getAdminName());
    }
}
