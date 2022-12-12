package TestDao;

import com.dhu.dao.AdminDao;
import com.dhu.domain.Admin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:spring-config.xml")
public class TestAdminDao {
    @Autowired
    AdminDao adminDao;

    //根据AdminId查询管理员信息
    @Test
    public void test_adminSelectById() {
        String id1="200800118",id2="";
        //存在
        Admin admin1 = adminDao.adminSelectById(id1);
        Assertions.assertEquals(id1, admin1.getAdminId());
        // 不存在
        Admin admin2 = adminDao.adminSelectById(id2);
        Assertions.assertNull(admin2);
    }

    //添加管理员
    @Test
    public void test_adminInsert() {
        Admin admin=new Admin("123456789","root");
        int result= adminDao.adminInsert(admin);
        Assertions.assertTrue(result>0);
    }
}
