package com.godfunc.modules.security.handler;

import com.godfunc.result.ApiCodeMsg;
import com.godfunc.result.R;
import com.godfunc.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class SecurityAccessDefinedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("资源访问受限", accessDeniedException);
        ResponseUtils.out(response, R.fail(ApiCodeMsg.NOPERMISSION));
    }
}