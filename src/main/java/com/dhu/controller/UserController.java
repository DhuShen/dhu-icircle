package com.dhu.controller;

import com.dhu.domain.User;
import com.dhu.service.UserService;
import com.dhu.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    //登录
    @RequestMapping("/login")
    public Result<Boolean> login(@RequestParam String id, @RequestParam String password, HttpServletRequest request) {
        User user = userService.userLogin(id, password);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            return new Result<>(Result.GET_OK, true, null);
        }
        return new Result<>(Result.GET_ERR, false, null);
    }

    //注册
    @RequestMapping("/register")
    public Result<Boolean> register(@RequestParam String id, @RequestParam String password) {
        boolean flag = userService.userRegister(id, password);
        return new Result<>(flag ? Result.SAVE_OK : Result.SAVE_ERR, false, null);
    }

    //登出
    @RequestMapping("/out")
    public Result<Boolean> out(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return new Result<>(Result.UPDATE_OK, true, null);
    }

    @RequestMapping("/update")
    public Result<Boolean> update(@RequestBody User user){
        return new Result<>(Result.UPDATE_OK,true,null);
    }

    //获取用户信息
    @RequestMapping("/info")
    public Result<User> getInfo(@RequestParam String id) {
        User user = userService.userCheck(id);
        if (user == null)
            return new Result<>(Result.GET_ERR, null, "用户不存在");
        else
            return new Result<>(Result.GET_OK, user, null);
    }

    //获取我的信息
    @RequestMapping("/myInfo")
    public Result<User> getMyInfo(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null)
            return new Result<>(Result.GET_ERR, null, "用户不存在");
        else
            return new Result<>(Result.GET_OK, user, null);
    }

    //获取我的名字
    @RequestMapping("/myName")
    public Result<String> getMyName(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null)
            return new Result<>(Result.GET_ERR, null, "用户不存在");
        else
            return new Result<>(Result.GET_OK, user.getUserName(), null);
    }

    //获取用户名字
    @RequestMapping("/name")
    public Result<String>  getName(@RequestParam String userId){
        return new Result<>(Result.GET_OK,"用户2233",null);
    }
}
