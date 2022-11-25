package com.dhu.service;

import com.dhu.domain.Circle;
import com.dhu.domain.view.CircleView;

import java.util.List;

public interface CircleService {
//	用户主页
//    c)	查找圈子、贴子
    List<Circle> searchCircle(String name);//查询圈子
//    d)	浏览圈子、帖子、评论
    List<CircleView> getHotCircle();//获取热门圈子排序
    List<Circle> getMyCircle(String id);//获取我的圈子

}
