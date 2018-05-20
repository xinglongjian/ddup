package com.xingtan.school.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.*;

/**
 * 相册
 * Created by xinglongjian on 5/20 2018 10:02.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class GradeAlbum extends BaseEntity {
    /**
     * 班级Id
     */
    private long gradeId;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String info;
    /**
     * 创建者ID
     */
    private long createdUserId;

}
