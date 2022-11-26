package com.dhu.dao;

import com.dhu.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User userSelectById(@Param("userId") String userId);

    boolean userInsert(User user);

    int closeUser(@Param("userId") String userId);
}
