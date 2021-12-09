package com.godfunc.modules.sys.enums;

/**
 * 菜单类型
 * @author godfunc
 * @email godfunc@outlook.com
 * @date 2020/7/31
 */
public enum MenuTypeEnum {

    /**
     * 菜单
     */
    MENU(1),
    /**
     * 按钮
     */
    BUTTON(2);

    private final int value;

    MenuTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
