package com.dhu.dao;

import com.dhu.domain.Request;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RequestDao {
    //增加请求
    int insertRequest(Request request);

    //以发起人id排序查询所有请求
    List<Request> selectAllOrder();

    boolean setRequestChecked(@Param("requestId") int requestId);

    Request getRequestInfo(@Param("requestId")int requestId);
}
