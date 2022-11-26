package com.dhu.dao;

import com.dhu.domain.Circle;
import com.dhu.domain.view.CircleView;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CircleDao {
    List<Circle> selectByName(@Param("circleName") String circleName);

    List<CircleView> selectTen();

    List<Circle> selectById(@Param("userId") String userId);

}
