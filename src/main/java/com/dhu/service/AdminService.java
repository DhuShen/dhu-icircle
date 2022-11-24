package com.dhu.service;

import com.dhu.dao.AdminDao;
import com.dhu.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;

public interface AdminService {
    Admin login(String id,String pwd);
}
