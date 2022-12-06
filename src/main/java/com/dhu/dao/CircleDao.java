package com.dhu.dao;

import com.dhu.domain.Circle;
import com.dhu.domain.User;
import com.dhu.domain.view.CircleView;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CircleDao {
    //根据圈子名字模糊查询圈子
    List<Circle> selectByName(@Param("circleName") String circleName);

    //查询热门圈子
    List<CircleView> selectTen();

    //查询一个用户管理（圈主）的圈子
    List<Circle> selectById(@Param("userId") String userId);

    //增加圈子
    int insertCircle(Circle circle);

    //根据circleId查询圈子
    Circle selectByCircleId(@Param("circleId") int circleId);

    //根据circleId注销圈子
    int deleteCircle(@Param("circleId") int circleId);

    //修改圈子的圈主
    int updateCircleMaster(Circle circle);

    //修改圈子的信息
    int updateCircleContent(Circle circle);

    //加入圈子（增加关系）
    int insertConnection(@Param("userId") String userId, @Param("circleId") int circleId);

    //退出圈子（删除关系）
    int deleteConnection(@Param("userId") String userId, @Param("circleId") int circleId);

    //根据userId和circleId查询用户是否在圈子中
    String selectConnection(@Param("userId") String userId, @Param("circleId") int circleId);

    //查询用户是否为圈主
    String IsCircleMaster(@Param("circle_UserId") String circle_UserId, @Param("circleId") int circleId);
    //根据circleId查询圈子里所有成员
    List<User> selectUserInCircle(@Param("circleId") int circleId);

    //根据userId查询该用户加入的圈子
    List<Circle> selectConnectionById(@Param("userId") String userId);
}
