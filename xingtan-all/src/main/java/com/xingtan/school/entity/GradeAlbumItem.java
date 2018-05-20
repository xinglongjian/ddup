package com.xingtan.school.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.*;

/**
 * Created by xinglongjian on 5/20 0020 10:46.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class GradeAlbumItem extends BaseEntity {

    /**
     * 对应的上传ID
     */
    private long albumUploadId;
    /**
     * 照片路径
     */
    private String path;

}
