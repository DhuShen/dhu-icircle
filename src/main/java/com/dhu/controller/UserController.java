package com.dhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/user")
public class UserController {
    @RequestMapping(value = "/login")
    public boolean login(String userId, String password, HttpServletRequest request) {
        return true;
    }
}
