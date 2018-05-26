package com.xingtan.school.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.*;

import javax.naming.ldap.PagedResultsControl;

/**
 * Created by xinglongjian on 5/20 0020 10:45.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class GradeAlbumUpload extends BaseEntity {
    private long gradeId;
    /**
     * 相册ID
     */
    private long albumId;
    /**
     * 位置
     */
    private String position;
    /**
     * 描述
     */
    private String info;
    /**
     * 上传者
     */
    private long createdUserId;

}
