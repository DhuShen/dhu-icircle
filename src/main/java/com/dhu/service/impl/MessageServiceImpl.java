package com.dhu.service.impl;

import com.dhu.dao.MessageDao;
import com.dhu.domain.Message;
import com.dhu.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageDao messageDao;

    public List<Message> getMessage(String getId) {
        return messageDao.selectByGetId(getId);
    }
}
