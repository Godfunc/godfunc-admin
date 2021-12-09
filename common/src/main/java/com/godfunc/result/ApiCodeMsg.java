package com.godfunc.result;

/**
 * @author Godfunc
 * @date 2019/12/1 21:17
 */
public enum ApiCodeMsg {

    /**
     * 失败
     */
    FAILED(ApiCode.FAIL, ApiMsg.FAIL),
    /**
     * 成功
     */
    SUCCESS(ApiCode.SUCCESS, ApiMsg.SUCCESS),

    // token过期
    UNAUTHORIZED(ApiCode.UNAUTHORIZED, ApiMsg.UNAUTHORIZED),

    // 没有权限
    NOPERMISSION(ApiCode.NO_PERMISSION, ApiMsg.NO_PERMISSION),

    // 其他错误 （网络异常）
    OTHER(ApiCode.OTHER, ApiMsg.OTHER),

    // 数据库数据重复
    DATA_DUPLICATION(ApiCode.DATA_DUPLICATION, ApiMsg.DATA_DUPLICATION),

    // 请求body不存在
    REQUEST_BOY_IS_EMPTY(ApiCode.PARAM_ERROR, ApiMsg.REQUEST_BOY_IS_EMPTY);

    private final long code;
    private final String msg;

    ApiCodeMsg(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }


    public long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return String.format(" ApiCode:{code=%s, msg=%s} ", code, msg);
    }
}
