package com.xingtan.school.service;

import com.xingtan.school.entity.GradeValidateMessage;

import java.util.List;

public interface GradeValidateMessageService {
    List<GradeValidateMessage> getMessageBySendUserId(long sendUserId);
    List<GradeValidateMessage> getMessageByValidateUserId(long validateUserId);
    GradeValidateMessage getMessageById(long id);
    long insertMessage(GradeValidateMessage message);
    void updateMessage(GradeValidateMessage message);
    void deleteMessage(long id);
}
