package com.dhu.controller;

import com.dhu.domain.Message;
import com.dhu.domain.User;
import com.dhu.service.MessageService;
import com.dhu.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    //获取消息
    @RequestMapping("/get")
    public Result<List<Message>> get(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return new Result<>(Result.GET_ERR, null, "用户不存在");
        } else {
            List<Message> messages = messageService.getMessage(user.getUserId());
            return new Result<>(Result.GET_OK, messages, null);
        }
    }
}
