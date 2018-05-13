package com.xingtan.school.bean;

import com.xingtan.common.entity.GradeLevel;
import com.xingtan.common.entity.GradeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by xinglongjian on 5/13 2018 14:36.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GradeItemData extends GradeData {
    /**
     * 教师数量
     */
    private int teacherNum;
    /**
     * 学生数量
     */
    private int studentNum;
}
