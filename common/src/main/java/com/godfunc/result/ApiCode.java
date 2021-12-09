package com.godfunc.result;

/**
 * @author Godfunc
 * @email godfunc@outlook.com
 */
public interface ApiCode {

    int UNAUTHORIZED = 401;

    /** 没有权限 */
    int NO_PERMISSION = 1010;
    /** 数据重复 */
    int DATA_DUPLICATION = 1011;

    /** 数据不存在 */
    int DATA_NOT_EXIST = 1012;

    /** 数据已存在 */
    int DATA_EXIST = 1013;

    /** 数据不能为空 */
    int NOT_BE_BLANK = 1014;

    /** 数据不正确 */
    int DATA_ERROR = 1017;

    /** 参数有误 */
    int PARAM_ERROR = 1022;

    int SUCCESS = 0;

    int FAIL = -1;

    int OTHER = 1088;
}
