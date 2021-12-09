package com.godfunc.interceptor;

import com.godfunc.annotation.Login;
import com.godfunc.constant.ApiConstant;
import com.godfunc.entity.UserToken;
import com.godfunc.exception.GException;
import com.godfunc.result.ApiCodeMsg;
import com.godfunc.service.UserTokenService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final UserTokenService userTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Login annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        } else {
            return true;
        }

        if (annotation == null) {
            return true;
        }

        //从header中获取token
        String token = request.getHeader(ApiConstant.TOKEN_HEADER);
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(ApiConstant.TOKEN_HEADER);
        }

        //token为空
        if (StringUtils.isBlank(token)) {
            throw new GException(ApiCodeMsg.UNAUTHORIZED);
        }

        //查询token信息
        UserToken userToken = userTokenService.getByToken(token);
        if (userToken == null || userToken.getExpireTime().isBefore(LocalDateTime.now())) {
            throw new GException(ApiCodeMsg.UNAUTHORIZED);
        }
        long between = Duration.between(LocalDateTime.now(), userToken.getExpireTime()).toMinutes();
        if (between > 0 && between < ApiConstant.EXPIRED_PLUS) {

            userTokenService.updateExpired(userToken);
        }
        request.setAttribute(ApiConstant.USER_ID, userToken.getUserId());
        return true;
    }
}
