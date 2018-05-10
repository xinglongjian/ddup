package com.xingtan.school.bean;

import lombok.*;

import java.io.Serializable;

/**
 * Created by xinglongjian on 5/9 0009 22:27.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentSchool implements Serializable {
    private long studentId;
    private long schoolId;
}
