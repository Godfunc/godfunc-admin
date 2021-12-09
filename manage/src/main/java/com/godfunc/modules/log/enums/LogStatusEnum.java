package com.godfunc.modules.log.enums;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 * @date 2020/7/31
 */
public enum LogStatusEnum {
    /**
     * 日志记录方法执行失败是 0
     */
    FAIL(0),
    /**
     * 日志记录方法执行成功是 1
     */
    SUCCESS(1);

    private final int value;

    LogStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
