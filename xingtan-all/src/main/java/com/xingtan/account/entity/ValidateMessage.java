package com.xingtan.account.entity;

import com.xingtan.common.entity.BaseEntity;
import com.xingtan.common.entity.ValidateResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 简单的验证消息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateMessage extends BaseEntity {

    private long sendUserId;

    private String message;

    private long validateUserId;

    private ValidateResult result;

}
