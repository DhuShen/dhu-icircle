package com.dhu.service;

import com.dhu.domain.Message;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MessageService {
    //    h)	用户查看消息（消息）【在message中】
    List<Message> getMessage(String getId);

    //    e)	处理信息发送消息【在message中】
    boolean setMessage(String messageContent, String setId, String getId);
}
