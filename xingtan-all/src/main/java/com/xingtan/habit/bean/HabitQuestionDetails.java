package com.xingtan.habit.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author xinglongjian@qq.com
 * @Date 2018/7/8/008 21:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HabitQuestionDetails implements Serializable {
    private long habitId;
    private String title;
    private List<HabitQuestionData> questions;
}
