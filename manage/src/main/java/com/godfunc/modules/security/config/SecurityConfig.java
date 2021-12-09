package com.godfunc.modules.security.config;

import com.godfunc.modules.security.service.CaptchaService;
import com.godfunc.modules.security.service.UserTokenService;
import com.godfunc.modules.security.filter.AuthenticationFilter;
import com.godfunc.modules.security.filter.UsernamePasswordJsonAuthenticationFilter;
import com.godfunc.modules.security.handler.NoAuthEntryPoint;
import com.godfunc.modules.security.handler.SecurityAccessDefinedHandler;
import com.godfunc.modules.security.handler.SecurityLogoutHandler;
import com.godfunc.modules.sys.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final UserTokenService userTokenService;
    private final CaptchaService captchaService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling().authenticationEntryPoint(new NoAuthEntryPoint())
                .accessDeniedHandler(new SecurityAccessDefinedHandler())
                .and().csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and().logout().addLogoutHandler(new SecurityLogoutHandler(userService))
                .and().addFilterAt(new UsernamePasswordJsonAuthenticationFilter(authenticationManager(), userTokenService, captchaService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new AuthenticationFilter(userService, userTokenService), LogoutFilter.class).httpBasic();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/captcha", "/webjars/**", "/v3/api-docs", "/swagger-ui/**", "/swagger-resources/**", "/favicon.ico");
    }
}
