package com.dhu.service;

import com.dhu.domain.Message;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MessageService {
//    h)	用户查看消息（消息）(在message中）
    public List<Message> getMessage(String getId);

}
