package com.xingtan.habit.entity;

import com.xingtan.common.entity.BaseEntity;
import com.xingtan.common.entity.HabitQuestionType;
import lombok.*;

/**
 *  每一个habit都会包含多个item，这里item会以选择题，判断题，填空题不同形式出现，
 *  每一个item可能会在多个habit里出现。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class HabitQuestion extends BaseEntity {
    /**
     * 问题类型，选择题，判断题
     */
    private HabitQuestionType type;
    /**
     * 题干
     */
    private String title;
    /**
     * 权重
     */
    private float weight;

    //身体上影响
    //心理上影响
    /**
     * 该项内容介绍
     */
    private String introduce;

    /**
     * 好处
     */
    private String good;
    /**
     * 坏处
     */
    private String bad;
}
