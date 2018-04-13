package com.xingtan.school.mapper;

import com.xingtan.school.entity.GradeValidateMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GradeValidateMessageMapper {
    List<GradeValidateMessage> getMessageBySendUserId(@Param("sendUserId") long sendUserId);
    List<GradeValidateMessage> getMessageByValidateUserId(@Param("validateUserId") long validateUserId);
    GradeValidateMessage getMessageById(@Param("id") long id);
    void insertMessage(GradeValidateMessage message);
    void updateMessage(GradeValidateMessage message);
    void deleteMessage(@Param("id") long id);
}
