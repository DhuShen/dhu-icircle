package com.dhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/recommend")
    public String recommend(){
        return "recommend";
    }

    @RequestMapping("/circles")
    public String circles(){
        return "circles";
    }

    @RequestMapping("/mail")
    public String mail(){
        return "mail";
    }
}