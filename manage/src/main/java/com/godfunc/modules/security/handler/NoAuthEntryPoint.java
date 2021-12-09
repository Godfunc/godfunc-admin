package com.godfunc.modules.security.handler;

import com.godfunc.result.ApiCodeMsg;
import com.godfunc.result.R;
import com.godfunc.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class NoAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("授权错误", authException);
        ResponseUtils.out(response, R.fail(ApiCodeMsg.UNAUTHORIZED));
    }
}