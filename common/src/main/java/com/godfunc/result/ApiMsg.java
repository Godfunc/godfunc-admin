package com.godfunc.result;

/**
 * @author Godfunc
 * @date 2019/12/1 21:37
 */
public interface ApiMsg {

    String SUCCESS = "success";

    String FAIL = "fail";

    /** 数据重复 */
    String DATA_DUPLICATION = "数据已存在";

    String UNAUTHORIZED = "登录信息已过期，请重新登录";

    String NO_PERMISSION = "没有访问权限";
    
    String OTHER = "网络异常，请稍后再试";

    String REQUEST_BOY_IS_EMPTY = "请求内容不能为空";
}
