package com.xingtan.habit.mapper;

import com.xingtan.habit.entity.UserHabitRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserHabitRelationMapper {
    UserHabitRelation getRelationById(@Param("id") long id);

    List<UserHabitRelation> getRecordsByUserId(@Param("userId") long userId);

    void insertRecord(UserHabitRelation userHabitRelation);

    void insertBatchRelations(List<UserHabitRelation> list);

    void updateRecord(UserHabitRelation userHabitRelation);

    void deleteRecord(@Param("id") long id);

    void deleteBatchRecord(@Param("ids") List<Long> ids);
}
