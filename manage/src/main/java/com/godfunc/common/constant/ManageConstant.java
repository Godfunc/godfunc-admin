package com.godfunc.common.constant;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
public interface ManageConstant {
    String TOKEN_HEADER = "G-Token";

    /**
     * token 有效时间 秒
     */
    long TOKEN_EXPIRED = 60 * 60 * 24 * 5;

    /**
     * 快过期时间
     */
    long EXPIRED_PLUS = 5;


    String LOGIN_PATH = "/login";
}
