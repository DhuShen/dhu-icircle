package com.dhu.controller;

import com.dhu.domain.Admin;
import com.dhu.service.AdminService;
import com.dhu.service.impl.AdminServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("adminLogin")
    public void adminLogin() {
        System.out.println("in");
        AdminService adminService = new AdminServiceImpl();
        Admin admin=adminService.login("200800118","hhn");
        System.out.println(admin.getAdminName());
    }
}
