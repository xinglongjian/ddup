package com.xingtan.school.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.*;

import java.util.Date;

/**
 * @Author zhengweiliang
 * @Date 2018/4/18 13:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class HomeWork extends BaseEntity {
    /**
     * 课程ID
     */
    private long courseId;
    /**
     * 名称
     */
    private String name;
    /**
     * 内容
     */
    private String content;
    /**
     * 谁留的
     */
    private long createUserId;

    private Date dd;
    private boolean is;

}
