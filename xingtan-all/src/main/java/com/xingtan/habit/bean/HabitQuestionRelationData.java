package com.xingtan.habit.bean;

import com.xingtan.habit.entity.Habit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author xinglongjian@qq.com
 * @Date 2018/7/5/005 21:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HabitQuestionRelationData implements Serializable {
    private Habit habit;
    private int relationCount;
}
