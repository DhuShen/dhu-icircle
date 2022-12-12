package TestServiceImpl;

import com.dhu.dao.MessageDao;
import com.dhu.domain.Message;
import com.dhu.service.MessageService;
import com.dhu.tools.MyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageDao messageDao;

    //    h)	用户查看消息（消息）【在message中】
    @Override
    public List<Message> getMessage(String getId) {
        return messageDao.selectByGetId(getId);
    }

    //    e)	处理信息发送消息【在message中】
    @Override
    public boolean setMessage(String messageContent, String setId, String getId) {
        Message message = new Message();
        message.setMessageContent(messageContent);
        message.setMessageUserIdSet(setId);
        message.setMessageUserIdGet(getId);
        message.setMessageSetTime(MyTime.getNowTime());
        return messageDao.insertMessage(message) > 0;
    }
}
