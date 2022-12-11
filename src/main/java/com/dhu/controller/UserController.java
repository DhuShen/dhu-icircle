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

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    //登录
    @RequestMapping("/login")
    public Result<Boolean> login(@RequestParam String id, @RequestParam String password, HttpSession session) {
        User user = userService.userLogin(id, password);
        if (user != null) {
            session.setAttribute("user", user);
            session.removeAttribute("admin");
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
    public Result<Boolean> out(HttpSession session) {
        session.removeAttribute("user");
        return new Result<>(Result.UPDATE_OK, true, null);
    }

    //更新用户信息
    @RequestMapping("/update")
    public Result<Boolean> update(@RequestBody User user, HttpSession session) {
        boolean flag = userService.updateUserInfo(user);
        if (flag) {
            session.setAttribute("user", userService.userCheck(user.getUserId()));
            return new Result<>(Result.UPDATE_OK, true, null);
        } else {
            return new Result<>(Result.UPDATE_ERR, true, "更新失败");
        }
    }

    //获取用户信息
    @RequestMapping("/getInfo")
    public Result<User> getInfo(@RequestParam String userId) {
        User user = userService.userCheck(userId);
        if (user == null)
            return new Result<>(Result.GET_ERR, null, "用户不存在");
        else
            return new Result<>(Result.GET_OK, user, null);
    }

    //获取我的信息
    @RequestMapping("/myInfo")
    public Result<User> getMyInfo(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return new Result<>(Result.GET_OK, user, null);
    }

    //获取我的名字
    @RequestMapping("/myName")
    public Result<String> getMyName(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return new Result<>(Result.GET_OK, user.getUserName(), null);
    }

    //获取用户名字
    @RequestMapping("/name")
    public Result<String> getName(@RequestParam String userId) {
        String name = userService.getUserNameById(userId);
        return new Result<>(Result.GET_OK, name, null);
    }

    //获取我的Id
    @RequestMapping("/myId")
    public Result<String> getMyId(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return new Result<>(Result.GET_OK, user.getUserId(), "获取个人id失败");
    }
}
