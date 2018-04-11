package com.xingtan.account.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
public class GradeValidateMessage extends ValidateMessage {

    private long gradeId;

    private long studentId;
}
