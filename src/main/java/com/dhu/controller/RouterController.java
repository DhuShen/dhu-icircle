package com.dhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RouterController {
    @RequestMapping("/login")
    public String login() {
        return "login.html";
    }

    @RequestMapping("/register")
    public String register() {
        return "register.html";
    }

    @RequestMapping("/recommend")
    public String recommend() {
        return "recommend.html";
    }

    @RequestMapping("/circles")
    public String circles() {
        return "circles.html";
    }

    @RequestMapping("/mail")
    public String mail() {
        return "mail.html";
    }

    @RequestMapping("/info")
    public String info() {
        return "info.html";
    }

    @RequestMapping("/singleCircle")
    public String singleCircle(@RequestParam Integer circleId) {
        return "single-circle.html?circleId=" + circleId;
    }

    @RequestMapping("/post")
    public String post(@RequestParam Integer postId) {
        return "post.html?postId=" + postId;
    }
}