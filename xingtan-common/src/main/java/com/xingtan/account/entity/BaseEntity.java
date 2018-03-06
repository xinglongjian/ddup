package com.xingtan.account.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable{

    /**
     * 主键
     */
    private long id;

    /**
     * 创建时间
     */
    private Date gmt_create;

    /**
     * 最后修改时间
     */
    private Date gmt_modified;

}
