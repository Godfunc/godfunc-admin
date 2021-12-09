package com.godfunc.controller;

import com.godfunc.annotation.Login;
import com.godfunc.annotation.LoginUser;
import com.godfunc.constant.ApiConstant;
import com.godfunc.dto.UserDTO;
import com.godfunc.entity.User;
import com.godfunc.result.R;
import com.godfunc.util.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Api(tags = "用户")
@RestController
@RequestMapping("user")
public class UserController {

    @Login
    @ApiOperation("用户信息")
    @GetMapping("info")
    public R info(@ApiIgnore @LoginUser User user) {
        return R.ok(ConvertUtils.source2Target(user, UserDTO.class));
    }

    @Login
    @ApiOperation("用户id")
    @GetMapping("getUserId")
    public R getUserId(@ApiIgnore @RequestAttribute(ApiConstant.USER_ID) Long userId) {
        return R.ok(userId);
    }
}
