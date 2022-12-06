package com.dhu.dao;

import com.dhu.domain.Request;

import java.util.List;

public interface RequestDao {
    //增加请求
    int insertRequest(Request request);

    //以发起人id排序查询所有请求
    List<Request> selectAllOrder();
}
