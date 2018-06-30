package com.xingtan.habit.bean;

import com.xingtan.habit.entity.HabitQuestionItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author xinglongjian@qq.com
 * @Date 2018/6/28/028 22:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HabitQuestionData implements Serializable {
    private long questionId;
    private String title;
    private List<HabitQuestionItem> items;
}
