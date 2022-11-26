package com.dhu.dao;

import com.dhu.domain.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao {
    List<Message> selectByGetId(@Param("getId") String getId);
}
