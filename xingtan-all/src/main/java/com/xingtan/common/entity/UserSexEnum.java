package com.xingtan.common.entity;

public enum UserSexEnum {
    /**
     * 未知
     */
    UNKNOWN,
    /**
     * 男
     */
    MAN,
    /**
     * 女
     */
    FAMALE;

    public static UserSexEnum of(int v) {
        for(UserSexEnum e: values()) {
            if(e.ordinal() == v) {
                return e;
            }
        }
        return UNKNOWN;
    }
}
