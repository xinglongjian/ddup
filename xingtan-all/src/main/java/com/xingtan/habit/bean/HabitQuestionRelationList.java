package com.xingtan.habit.bean;

import com.xingtan.habit.entity.HabitQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author xinglongjian@qq.com
 * @Date 2018/7/5/005 21:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HabitQuestionRelationList implements Serializable {
    private long habitId;
    private String title;
    private List<HabitQuestion> questions;
}
