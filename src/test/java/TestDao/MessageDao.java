package TestDao;

import com.dhu.domain.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao {
    //根据收件人查询消息
    List<Message> selectByGetId(@Param("getId") String getId);

    //添加消息
    int insertMessage(Message message);
}
