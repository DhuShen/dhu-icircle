package com.dhu.controller;

import com.dhu.domain.Circle;
import com.dhu.domain.view.CircleView;
import com.dhu.service.CircleService;
import com.dhu.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
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
        Circle circle = new Circle();
        circle.setCircleId(circleId);
        circle.setCircleName("杂谈圈子");
        circle.setCircleContent("欢迎来到我的杂谈圈子,大家可以畅所欲言，写出自己心里的想法！");
        circle.setCircleTime(new Date());
        circle.setCircle_UserId("200910514");
        return new Result<>(Result.GET_OK, circle, null);
    }

    //获取圈子名字
    @RequestMapping("/name")
    public Result<String> getName(@RequestParam String circleId){
        return new Result<>(Result.GET_OK,"杂谈圈子",null);
    }

    //获取圈子总人数
    @RequestMapping("/userCount")
    public Result<Integer> getUserCount(@RequestParam Integer circleId) {
        return new Result<>(Result.GET_OK, 20, null);
    }

    //获取圈子总贴数
    @RequestMapping("/postCount")
    public Result<Integer> getPostCount(@RequestParam Integer circleId){

        return new Result<>(Result.GET_OK,18,null);
    }

    //获取圈子总精华帖数
    @RequestMapping("/keypostCount")
    public Result<Integer> getKeyPostCount(@RequestParam Integer circleId){
        return new Result<>(Result.GET_OK,32,null);
    }

    //获取圈子总赞数
    @RequestMapping("/goodCount")
    public Result<Integer> getGoodCount(@RequestParam int circleId){
        return new Result<>(Result.GET_OK,100,null);
    }

    //获取热门圈子
    @RequestMapping("/hotcircles")
    public Result<List<CircleView>> hotCircles(){
        List<CircleView> circleViews=circleService.getHotCircle();
        return new Result<>(Result.GET_OK,circleViews,null);
    }
}
