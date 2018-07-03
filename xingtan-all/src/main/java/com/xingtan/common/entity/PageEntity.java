package com.xingtan.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author xinglongjian@qq.com
 * @Date 2018/7/3/003 21:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageEntity<T> implements Serializable {
    public static final int PAGESIZE = 10;
    //每页的记录数
    private int pageSize = PAGESIZE;
    // 当前页数
    private int pageNum = 1;
    // 记录总数
    private long allCount = 0;
    // 数据
    private List<T> datas;

    public long getAllPageNum() {
        long allPageNum = allCount / pageSize;
        long left = allCount % pageSize;
        if (left != 0) {
            allPageNum++;
        }
        return allPageNum;
    }


}
