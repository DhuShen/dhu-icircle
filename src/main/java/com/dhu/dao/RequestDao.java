package com.dhu.dao;

import com.dhu.domain.Request;

import java.util.List;

public interface RequestDao {

    int insertRequest(Request request);

    List<Request> selectAllOrder();
}
