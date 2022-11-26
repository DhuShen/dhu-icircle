package com.dhu.dao;

import com.dhu.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User userSelectById(@Param("id")String id);

    boolean userInsert(@Param("user")User user);
}
