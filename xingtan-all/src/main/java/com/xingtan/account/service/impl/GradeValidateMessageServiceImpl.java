package com.xingtan.account.service.impl;

import com.xingtan.account.entity.GradeValidateMessage;
import com.xingtan.account.mapper.GradeValidateMessageMapper;
import com.xingtan.account.service.GradeValidateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeValidateMessageServiceImpl implements GradeValidateMessageService {

    @Autowired
    private GradeValidateMessageMapper gradeValidateMessageMapper;

    @Override
    public List<GradeValidateMessage> getMessageBySendUserId(long sendUserId) {
        return gradeValidateMessageMapper.getMessageBySendUserId(sendUserId);
    }

    @Override
    public List<GradeValidateMessage> getMessageByValidateUserId(long validateUserId) {
        return gradeValidateMessageMapper.getMessageByValidateUserId(validateUserId);
    }

    @Override
    public GradeValidateMessage getMessageById(long id) {
        return gradeValidateMessageMapper.getMessageById(id);
    }

    @Override
    public long insertMessage(GradeValidateMessage message) {
        gradeValidateMessageMapper.insertMessage(message);
        return message.getId();
    }

    @Override
    public void updateMessage(GradeValidateMessage message) {
        gradeValidateMessageMapper.updateMessage(message);
    }

    @Override
    public void deleteMessage(long id) {
        gradeValidateMessageMapper.deleteMessage(id);
    }
}
