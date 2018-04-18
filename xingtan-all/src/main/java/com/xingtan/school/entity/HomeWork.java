package com.xingtan.school.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author zhengweiliang
 * @Date 2018/4/18 13:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HomeWork extends BaseEntity {

    private long courseId;

    private String name;
    /**
     *
     */
    private String content;
    /**
     * 谁留的
     */
    private long createUserId;

    private boolean is;

}
