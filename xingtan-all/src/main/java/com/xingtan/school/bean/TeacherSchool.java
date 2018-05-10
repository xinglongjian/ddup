package com.xingtan.school.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by xinglongjian on 5/9 0009 22:27.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherSchool implements Serializable {
    private long teacherId;
    private long schoolId;
}
