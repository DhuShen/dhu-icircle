package TestServiceImpl;

import com.dhu.dao.AdminDao;
import com.dhu.domain.Admin;
import com.dhu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    @Override
    public Admin login(String adminId, String adminPwd) {
        Admin admin = adminDao.adminSelectById(adminId);
        if (admin != null) {
            admin.setAdminPassword("");
            return admin;
        } else
            return null;
    }

    @Override
    public boolean register(String adminId, String adminPwd) {
        Admin admin = new Admin(adminId, adminPwd);
        return adminDao.adminInsert(admin) > 0;
    }
}
