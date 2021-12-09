package com.godfunc.modules.sys.enums;

/**
 * 超管状态
 *
 * @author godfunc
 * @email godfunc@outlook.com
 * @date 2020/7/31
 */
public enum SuperManagerEnum {

    /**
     * 非超级管理员
     */
    NOT_SUPER_MANAGER(0),
    /**
     * 超级管理员
     */
    SUPER_MANAGER(1);

    private final int value;

    SuperManagerEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
