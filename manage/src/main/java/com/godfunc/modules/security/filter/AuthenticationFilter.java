package com.godfunc.modules.security.filter;

import com.godfunc.common.constant.ManageConstant;
import com.godfunc.exception.GException;
import com.godfunc.modules.security.entity.UserToken;
import com.godfunc.modules.security.service.UserTokenService;
import com.godfunc.modules.sys.service.UserService;
import com.godfunc.result.ApiCodeMsg;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Token授权
 */
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final UserTokenService userTokenService;
    private final AntPathRequestMatcher matcher = new AntPathRequestMatcher(ManageConstant.LOGIN_PATH, HttpMethod.POST.name());

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader(ManageConstant.TOKEN_HEADER);
        if (StringUtils.isNotBlank(token)) {
            UserToken userToken = userTokenService.getByToken(token);
            if (userToken == null) {
                throw new PreAuthenticatedCredentialsNotFoundException("token无效的token");
            }
            LocalDateTime now = LocalDateTime.now();
            long between = Duration.between(now, userToken.getExpireTime()).toMinutes();
            if (userToken.getExpireTime().isBefore(now)) {
                throw new GException(ApiCodeMsg.UNAUTHORIZED);
            } else if (between > 0 && between < ManageConstant.EXPIRED_PLUS) {
                // 如果快过期了，将token过期时间延长
                userTokenService.plusExpire(userToken);
            }
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userService.getByUserId(userToken.getUserId());
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return matcher.matches(request);
    }
}