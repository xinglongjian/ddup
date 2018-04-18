package com.xingtan.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author zhengweiliang
 * @Date 2018/4/17 8:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseLabel extends BaseEntity {
    //名称
    private String name;
    // 分类
    private String type;
}
