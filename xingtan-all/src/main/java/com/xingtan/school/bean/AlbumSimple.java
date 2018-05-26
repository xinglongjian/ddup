package com.xingtan.school.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by xinglongjian on 5/21 0021 23:30.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AlbumSimple implements Serializable {
    //
    private long id;
    private String name;
    // 该相册下照片数量
    private long count;
    private long gradeId;
    private long uploadId;
    private String fileName;
}
