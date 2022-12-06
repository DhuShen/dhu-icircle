import com.dhu.dao.UserDao;
import com.dhu.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:spring-config.xml")
public class TestUser {
    @Autowired
    UserDao userDao;

    @Test    //添加用户
    public void test_userInsert() {
        User user = new User("200800118", "root");
        userDao.userInsert(user);
    }

    @Test    //查询返回对应用户信息
    public void test_userSelectById() {
        User user = userDao.userSelectById("200800118");
        user.toString();
    }


    @Test    //修改用户一般信息
    public void test_updateUserInfo() {

    }

    @Test    //修改用户密码
    public void test_updateUserPwd() {

    }


    @Test    //封号用户（设置userLife等于1）
    public void test_closeUser() {

    }


}

