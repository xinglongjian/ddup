package com.xingtan.customer.entity;

import com.xingtan.common.entity.BaseEntity;
import lombok.*;

/**
 * 问题类型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class QuestionType extends BaseEntity {
    /**
     * 名称
     */
    private String name;
}
