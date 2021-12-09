package com.godfunc.controller;

import com.godfunc.annotation.Login;
import com.godfunc.constant.ApiConstant;
import com.godfunc.param.LoginParam;
import com.godfunc.param.RegisterParam;
import com.godfunc.result.R;
import com.godfunc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Api(tags = "登录注册")
@RequiredArgsConstructor
@RestController
public class LoginController {

    private final UserService userService;

    @ApiOperation("登录")
    @PostMapping("login")
    public R login(@Valid @RequestBody LoginParam param) {
        return R.ok(userService.login(param));
    }

    @Login
    @ApiOperation("登出")
    @PostMapping("logout")
    public R logout(@ApiIgnore @RequestAttribute(ApiConstant.USER_ID) Long userId) {
        userService.logout(userId);
        return R.ok();
    }

    @ApiOperation("注册")
    @PostMapping("register")
    public R register(@Valid @RequestBody RegisterParam param) {
        return R.ok(userService.register(param));
    }
}
