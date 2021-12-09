package com.godfunc.modules.security.util;

import com.godfunc.exception.GException;
import com.godfunc.modules.sys.model.UserDetail;
import com.godfunc.result.ApiCodeMsg;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
public class SecurityUser {

    public static Authentication getSubject() {
        try {
            return SecurityContextHolder.getContext().getAuthentication();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取用户信息
     */
    public static UserDetail getUser() {
        Authentication authentication = getSubject();
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new GException(ApiCodeMsg.UNAUTHORIZED);
        }
        UserDetail user = (UserDetail) authentication.getPrincipal();
        if (Objects.isNull(user)) throw new GException(ApiCodeMsg.UNAUTHORIZED);
        return user;
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        UserDetail user = getUser();
        if (Objects.isNull(user)) {
            throw new GException(ApiCodeMsg.UNAUTHORIZED);
        } else {
            return user.getId();
        }
    }


}