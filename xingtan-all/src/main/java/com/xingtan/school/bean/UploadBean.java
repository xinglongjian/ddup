package com.xingtan.school.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by xinglongjian on 5/27 0027 17:31.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UploadBean implements Serializable {
    private String name;
    private String position;
    private String info;
    private long gradeId;
    private long userId;
}
