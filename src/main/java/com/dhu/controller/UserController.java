package com.dhu.controller;

import com.dhu.domain.User;
import com.dhu.service.UserService;
import com.dhu.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public Result<Boolean> login(@RequestParam String id, @RequestParam String password, HttpServletRequest request) {
        return new Result<Boolean>(Result.GET_OK, true, null);
//        User user = userService.userLogin(id, password);
//        if (user != null) {
//            request.getSession().setAttribute("user", user);
//            return new Result<Boolean>(Result.GET_OK, true, null);
//        }
//        return new Result<Boolean>(Result.GET_ERR, false, null);
    }

    @RequestMapping("/register")
    public Result<Boolean> register(@RequestParam String id, @RequestParam String password) {
        return new Result<Boolean>(Result.SAVE_OK, true, null);
//        boolean flag = userService.userRegister(id, password);
//        return new Result<Boolean>(flag ? Result.SAVE_OK : Result.SAVE_ERR, false, null);
    }

    @RequestMapping("/out")
    public Result<Boolean> out(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return new Result<Boolean>(Result.UPDATE_OK, true, null);
    }
}
