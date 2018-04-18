package com.xingtan.school.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author zhengweiliang
 * @Date 2018/4/18 13:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HomeWorkRecord extends BaseEntity {
    /**
     * 作业ID
     */
    private long homeWorkId;
    /**
     * 学生ID
     */
    private long studentId;
    /**
     * 创建者
     */
    private long createUserId;
}
