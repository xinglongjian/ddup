package com.xingtan.account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GradeValidateMessage extends ValidateMessage {

    private long gradeId;

    private long studentId;
}
