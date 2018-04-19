package com.xingtan.common.entity;

import lombok.*;

/**
 * @Author zhengweiliang
 * @Date 2018/4/17 8:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class BaseLabel extends BaseEntity {
    //名称
    private String name;
    // 分类
    private String type;
}
