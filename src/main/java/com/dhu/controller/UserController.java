package com.dhu.controller;

import com.dhu.domain.User;
import com.dhu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login")
    public boolean login(String userId, String password, HttpServletRequest request) {
        User user = userService.login(userId, password);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            return true;
        }
        return false;
    }

    

}
