package com.dhu.controller;

import com.dhu.domain.Circle;
import com.dhu.domain.User;
import com.dhu.domain.view.CircleView;
import com.dhu.service.CircleService;
import com.dhu.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("api/circle")
public class CircleController {
    @Autowired
    CircleService circleService;

    //获取当前浏览的圈子信息
    @RequestMapping("/getInfo")
    public Result<Circle> getNowInfo(@RequestParam Integer circleId) {
        Circle circle = circleService.selectById(circleId);
        if (circle != null)
            return new Result<>(Result.GET_OK, circle, null);
        else
            return new Result<>(Result.GET_OK, null, "圈子获取失败");
    }

    //获取圈子名字
    @RequestMapping("/name")
    public Result<String> getName(@RequestParam Integer circleId) {
        String name = circleService.getCircleNameById(circleId);
        return new Result<>(Result.GET_OK, name, null);
    }

    //获取热门圈子
    @RequestMapping("/hotcircles")
    public Result<List<CircleView>> hotCircles() {
        List<CircleView> circleViews = circleService.getHotCircle();
        return new Result<>(Result.GET_OK, circleViews, null);
    }

    //是否在圈内
    @RequestMapping("/isAttention")
    public Result<Boolean> isAttention(@RequestParam Integer circleId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        boolean flag = circleService.isInCircle(user.getUserId(), circleId);
        return new Result<>(flag ? Result.GET_OK : Result.GET_ERR, flag, null);
    }

    //是否是圈主
    @RequestMapping("/isMaster")
    public Result<Boolean> isMaster(@RequestParam Integer circleId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        boolean flag = circleService.isCircleMaster(user.getUserId(), circleId);
        return new Result<>(flag ? Result.GET_OK : Result.GET_ERR, flag, null);
    }

    //关注圈子
    @RequestMapping("/enterCirlce")
    public Result<Boolean> enterCirlce(@RequestParam Integer circleId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        boolean flag = circleService.enterCircle(user.getUserId(), circleId);
        return new Result<>(flag ? Result.SAVE_OK : Result.SAVE_ERR, flag, null);
    }

    //取消关注
    @RequestMapping("/quitCirlce")
    public Result<Boolean> outCirlce(@RequestParam Integer circleId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        boolean flag = circleService.quitCircle(user.getUserId(), circleId);
        return new Result<>(flag ? Result.UPDATE_OK : Result.UPDATE_ERR, flag, null);
    }

    //踢人
    @RequestMapping("/deletePerson")
    public Result<Boolean> deletePerson(@RequestParam Integer circleId, @RequestParam String userId) {
        boolean flag = circleService.quitCircle(userId, circleId);
        return new Result<>(flag ? Result.UPDATE_OK : Result.UPDATE_ERR, flag, null);
    }

    //获取圈子里的所有人
    @RequestMapping("/getPerson")
    public Result<List<User>> getPerson(@RequestParam Integer circleId) {
        List<User> users = circleService.selectUserInCircle(circleId);
        return new Result<>(Result.GET_OK, users, null);
    }

    //查询我关注的圈子
    @RequestMapping("/getMyCircle")
    public Result<List<Circle>> getMyCircle(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Circle> circles = circleService.getInCircle(user.getUserId());
        return new Result<>(Result.GET_OK, circles, null);
    }

    //查询我管理的圈子
    @RequestMapping("/getMyMasterCircle")
    public Result<List<Circle>> getMyMasterCircle(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Circle> circles = circleService.getMyCircle(user.getUserId());
        return new Result<>(Result.GET_OK, circles, null);
    }

    //解散圈子
    @RequestMapping("/deleteCircle")
    public Result<Boolean> deleteCircle(@RequestParam Integer circleId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        boolean flag = circleService.dissolveCircle(user.getUserId(), circleId);
        return new Result<>(flag ? Result.DELETE_OK : Result.DELETE_ERR, flag, null);
    }

    //搜索圈子
    @RequestMapping("/search")
    public Result<List<Circle>> search(@RequestParam String name) {
        List<Circle> circles = circleService.searchCircle(name);
        return new Result<>(Result.GET_OK, circles, null);
    }
}