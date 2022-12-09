import com.dhu.dao.CircleDao;
import com.dhu.dao.UserDao;
import com.dhu.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:spring-config.xml")
public class TestCircle {
    @Autowired
    CircleDao circleDao;

    @Test    //加入圈子
    public void test_userInsert() {

    }

}

