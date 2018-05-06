package com.xingtan.common.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @param <T>
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {
    private HttpStatus status;
    private String message;
    private T data;

    public BaseResponse(HttpStatus status, T data) {
        this.status = status;
        this.data = data;
    }
}
