package com.xingtan.account.service;

import com.xingtan.account.entity.GradeValidateMessage;

import java.util.List;

public interface GradeValidateMessageService {
    List<GradeValidateMessage> getMessageBySendUserId(long sendUserId);
    List<GradeValidateMessage> getMessageByValidateUserId(long validateUserId);
    GradeValidateMessage getMessageById(long id);
    long insertMessage(GradeValidateMessage message);
    void updateMessage(GradeValidateMessage message);
    void deleteMessage(long id);
}
