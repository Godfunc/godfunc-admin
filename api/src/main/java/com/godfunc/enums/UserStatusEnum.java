

package com.godfunc.enums;

/**
 * 用户状态
 *
 * @author godfunc
 * @email godfunc@outlook.com
 */
public enum UserStatusEnum {
    /**
     * 状态停用 0
     */
    DISABLE(0),
    /**
     * 状态可用 1
     */
    ENABLE(1);

    private final int value;

    UserStatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
