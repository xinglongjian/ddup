package com.xingtan.school.entity;

import com.xingtan.common.entity.ValidateMessage;
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
