package com.dhu.dao;

import com.dhu.domain.Circle;
import com.dhu.domain.User;
import com.dhu.domain.view.CircleView;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CircleDao {
    List<Circle> selectByName(@Param("circleName") String circleName);

    List<CircleView> selectTen();

    List<Circle> selectById(@Param("userId") String userId);

    int insertCircle(Circle circle);

    Circle selectByCircleId(@Param("circleId") long circleId);

    int deleteCircle(@Param("circleId") long circleId);

    int updateCircleMaster(Circle circle);

    int updateCircleContent(Circle circle);

    int insertConnection(@Param("userId") String userId, @Param("circleId") long circleId);

    int deleteConnection(@Param("userId") String userId, @Param("circleId") long circleId);

    String selectConnection(@Param("userId") String userId, @Param("circleId") long circleId);

    List<User> selectUserInCircle(@Param("circleId") long circleId);

    List<Circle> selectConnectionById(@Param("userId")String userId);
}
