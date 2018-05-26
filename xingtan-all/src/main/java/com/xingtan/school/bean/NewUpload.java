package com.xingtan.school.bean;

import com.xingtan.school.entity.GradeAlbumItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xinglongjian on 5/26 0026 20:51.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewUpload implements Serializable {
    // 上传ID
    private long uploadId;
    private long gradeId;
    // 上传者
    private String uploadRealName;
    private String uploadNickName;
    // 上传日期
    private String uploadDate;
    //最多9个
    private List<String> fileList;
    private int leftNum;
    //显示相册的信息
    private AlbumSimple albumSimple;
}
