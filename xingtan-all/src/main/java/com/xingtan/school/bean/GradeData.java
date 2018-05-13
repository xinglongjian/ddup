package com.xingtan.school.bean;

import com.xingtan.common.entity.GradeLevel;
import com.xingtan.common.entity.GradeStatus;
import com.xingtan.school.entity.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by xinglongjian on 5/13 0013 15:09.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GradeData implements Serializable {
    private long id;
    private long schoolId;
    private String schoolName;
    private String name;
    private int year;
    private GradeLevel level = GradeLevel.BOTTOM;
    private GradeStatus status = GradeStatus.INITIALIZATION;

    public void fromGrade(Grade grade) {
        setId(grade.getId());
        setLevel(grade.getLevel());
        setName(grade.getName());
        setSchoolId(grade.getSchoolId());
        setStatus(grade.getStatus());
        setYear(grade.getYear());
    }

}
