package com.godfunc.modules.security.handler;

import com.godfunc.modules.sys.model.UserDetail;
import com.godfunc.modules.sys.service.UserService;
import com.godfunc.result.R;
import com.godfunc.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class SecurityLogoutHandler implements LogoutHandler {

    private final UserService userService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        userService.logout(userDetail.getId());
        ResponseUtils.out(response, R.ok());
    }
}
