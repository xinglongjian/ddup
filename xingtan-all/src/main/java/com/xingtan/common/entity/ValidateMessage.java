package com.xingtan.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 简单的验证消息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ValidateMessage extends BaseEntity {

    private long sendUserId;

    private String message;

    private long validateUserId;

    private ValidateResult result;

}
